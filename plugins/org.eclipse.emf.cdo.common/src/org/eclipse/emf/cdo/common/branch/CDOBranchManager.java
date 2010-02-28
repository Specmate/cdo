/**
 * Copyright (c) 2004 - 2010 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.common.branch;

import org.eclipse.net4j.util.event.INotifier;

/**
 * @author Eike Stepper
 * @since 3.0
 */
public interface CDOBranchManager extends INotifier
{
  public CDOBranch getMainBranch();

  public CDOBranch getBranch(int branchID);

  public CDOBranch getBranch(String path);

  public int getBranches(int startID, int endID, CDOBranchHandler handler);
}
