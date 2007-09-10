/***************************************************************************
 * Copyright (c) 2004 - 2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.internal.cdo;

import org.eclipse.emf.cdo.CDORevisionManager;
import org.eclipse.emf.cdo.internal.protocol.revision.CDORevisionImpl;
import org.eclipse.emf.cdo.internal.protocol.revision.CDORevisionResolverImpl;
import org.eclipse.emf.cdo.internal.protocol.revision.CDORevisionImpl.MoveableList;
import org.eclipse.emf.cdo.protocol.CDOID;
import org.eclipse.emf.cdo.analyzer.CDOFetchRuleManager;
import org.eclipse.emf.cdo.protocol.model.CDOFeature;
import org.eclipse.emf.cdo.protocol.revision.CDOReferenceProxy;
import org.eclipse.emf.cdo.protocol.util.TransportException;

import org.eclipse.net4j.IChannel;
import org.eclipse.net4j.signal.IFailOverStrategy;

import org.eclipse.emf.internal.cdo.protocol.LoadChunkRequest;
import org.eclipse.emf.internal.cdo.protocol.LoadRevisionByTimeRequest;
import org.eclipse.emf.internal.cdo.protocol.LoadRevisionByVersionRequest;
import org.eclipse.emf.internal.cdo.protocol.LoadRevisionRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Eike Stepper
 */
public class CDORevisionManagerImpl extends CDORevisionResolverImpl implements CDORevisionManager
{
  private CDOSessionImpl session;

  private CDOFetchRuleManager ruleManager = CDOFetchRuleManager.NOOP;

  public CDORevisionManagerImpl(CDOSessionImpl session)
  {
    this.session = session;
  }

  public CDOSessionImpl getSession()
  {
    return session;
  }

  public CDOID resolveReferenceProxy(CDOReferenceProxy referenceProxy)
  {
    // Get proxy values
    CDORevisionImpl revision = (CDORevisionImpl)referenceProxy.getRevision();
    CDOFeature feature = referenceProxy.getFeature();
    int accessIndex = referenceProxy.getIndex();

    // Get appropriate chunk size
    int chunkSize = session.getReferenceChunkSize();
    if (chunkSize == CDORevisionImpl.UNCHUNKED)
    {
      // Can happen if CDOSession.setReferenceChunkSize() was called meanwhile
      chunkSize = Integer.MAX_VALUE;
    }

    MoveableList list = revision.getList(feature);
    int size = list.size();
    int fromIndex = accessIndex;
    int toIndex = accessIndex;
    boolean minReached = false;
    boolean maxReached = false;
    boolean alternation = false;
    for (int i = 0; i < chunkSize; i++)
    {
      if (alternation)
      {
        if (!maxReached && toIndex < size - 1 && list.get(toIndex + 1) instanceof CDOReferenceProxy)
        {
          ++toIndex;
        }
        else
        {
          maxReached = true;
        }

        if (!minReached)
        {
          alternation = false;
        }
      }
      else
      {
        if (!minReached && fromIndex > 0 && list.get(fromIndex - 1) instanceof CDOReferenceProxy)
        {
          --fromIndex;
        }
        else
        {
          minReached = true;
        }

        if (!maxReached)
        {
          alternation = true;
        }
      }

      if (minReached && maxReached)
      {
        break;
      }
    }

    try
    {
      IFailOverStrategy failOverStrategy = session.getFailOverStrategy();
      IChannel channel = session.getChannel();
      LoadChunkRequest request = new LoadChunkRequest(channel, revision, feature, accessIndex, fromIndex, toIndex);
      return failOverStrategy.send(request);
    }
    catch (RuntimeException ex)
    {
      throw ex;
    }
    catch (Exception ex)
    {
      throw new TransportException(ex);
    }
  }

  public List<Integer> analyzeReferenceRanges(List<Object> list)
  {
    List<Integer> ranges = new ArrayList<Integer>(0);
    int range = 0; // Not in range
    int lastIndex = -1; // Not in proxy range
    for (Object value : list)
    {
      if (lastIndex == -1)
      {
        // Not in proxy range
        if (value instanceof CDOReferenceProxy)
        {
          if (range != 0)
          {
            ranges.add(range);
            range = 0;
          }

          CDOReferenceProxy proxy = (CDOReferenceProxy)value;
          lastIndex = proxy.getIndex();
          --range;
        }
        else
        {
          ++range; // One more non-proxy
        }
      }
      else
      {
        // In proxy range
        if (value instanceof CDOReferenceProxy)
        {
          CDOReferenceProxy proxy = (CDOReferenceProxy)value;
          int index = proxy.getIndex();
          if (index == lastIndex + 1)
          {
            --range;
          }
          else
          {
            ranges.add(range);
            range = -1;
          }

          lastIndex = index;
        }
        else
        {
          if (range != 0)
          {
            ranges.add(range);
            range = 1;
          }

          lastIndex = -1;
        }
      }
    }

    if (range != 0)
    {
      ranges.add(range);
    }

    int size = ranges.size();
    if (size == 0 || size == 1 && ranges.get(0) > 0)
    {
      return null;
    }

    return ranges;
  }

  @Override
  protected CDORevisionImpl loadRevision(CDOID id, int referenceChunk)
  {
    return send(new LoadRevisionRequest(session.getChannel(), id, referenceChunk)).get(0);
  }

  @Override
  protected CDORevisionImpl loadRevisionByTime(CDOID id, int referenceChunk, long timeStamp)
  {
    return send(new LoadRevisionByTimeRequest(session.getChannel(), id, referenceChunk, timeStamp)).get(0);
  }

  @Override
  protected CDORevisionImpl loadRevisionByVersion(CDOID id, int referenceChunk, int version)
  {
    return send(new LoadRevisionByVersionRequest(session.getChannel(), id, referenceChunk, version)).get(0);
  }

  @Override
  protected List<CDORevisionImpl> loadRevisions(Collection<CDOID> ids, int referenceChunk)
  {
    return send(new LoadRevisionRequest(session.getChannel(), ids, referenceChunk));
  }

  @Override
  protected List<CDORevisionImpl> loadRevisionsByTime(Collection<CDOID> ids, int referenceChunk, long timeStamp)
  {
    return send(new LoadRevisionByTimeRequest(session.getChannel(), ids, referenceChunk, timeStamp));
  }

  private List<CDORevisionImpl> send(LoadRevisionRequest request)
  {
    try
    {
      IFailOverStrategy failOverStrategy = session.getFailOverStrategy();
      return failOverStrategy.send(request);
    }
    catch (RuntimeException ex)
    {
      throw ex;
    }
    catch (Exception ex)
    {
      throw new TransportException(ex);
    }
  }

  public CDOFetchRuleManager getRuleManager()
  {
    return ruleManager;
  }

  public void setRuleManager(CDOFetchRuleManager ruleManager)
  {
    this.ruleManager = ruleManager;
  }
}
