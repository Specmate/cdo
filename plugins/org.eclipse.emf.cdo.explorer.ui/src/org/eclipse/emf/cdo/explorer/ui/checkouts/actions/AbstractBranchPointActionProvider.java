/*
 * Copyright (c) 2009-2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.explorer.ui.checkouts.actions;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.branch.CDOBranchManager;
import org.eclipse.emf.cdo.common.branch.CDOBranchPoint;
import org.eclipse.emf.cdo.common.commit.CDOCommitInfo;
import org.eclipse.emf.cdo.common.util.CDOCommonUtil;
import org.eclipse.emf.cdo.explorer.CDOExplorerUtil;
import org.eclipse.emf.cdo.explorer.checkouts.CDOCheckout;
import org.eclipse.emf.cdo.explorer.repositories.CDORepository;
import org.eclipse.emf.cdo.explorer.ui.bundle.OM;
import org.eclipse.emf.cdo.internal.ui.dialogs.AbstractBranchPointDialog;
import org.eclipse.emf.cdo.internal.ui.dialogs.SelectCommitDialog;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.ui.shared.SharedIcons;
import org.eclipse.emf.cdo.view.CDOView;

import org.eclipse.net4j.util.ObjectUtil;
import org.eclipse.net4j.util.ui.actions.LongRunningAction;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @author Eike Stepper
 */
public abstract class AbstractBranchPointActionProvider extends AbstractActionProvider<CDOCheckout>
{
  private static final ImageDescriptor CHECKOUT_IMAGE_DESCRIPTOR = OM.getImageDescriptor("icons/checkout.gif");

  private static final ImageDescriptor CHECKOUT_CLOSED_IMAGE_DESCRIPTOR = OM
      .getImageDescriptor("icons/checkout_closed.gif");

  public AbstractBranchPointActionProvider(String id, String title)
  {
    super(CDOCheckout.class, id, title, ICommonMenuConstants.GROUP_ADDITIONS);
  }

  @Override
  protected final boolean fillSubMenu(ICommonViewerWorkbenchSite viewSite, IMenuManager subMenu, CDOCheckout checkout)
  {
    if (checkout.isOpen())
    {
      IWorkbenchPage page = viewSite.getPage();
      subMenu.add(new Separator("top"));
      fillSubMenu(page, subMenu, checkout);
      return true;
    }

    return false;
  }

  protected void fillSubMenu(IWorkbenchPage page, IMenuManager subMenu, CDOCheckout checkout)
  {
    CDOBranchPoint checkoutBranchPoint = checkout.getBranchPoint();

    // Repository of offline checkout is not necessarily open.
    if (checkout.getRepository().isConnected())
    {
      subMenu.add(new Separator("historized"));
      for (CDOBranchPoint branchPoint : checkout.getBranchPoints())
      {
        if (!ObjectUtil.equals(branchPoint, checkoutBranchPoint))
        {
          subMenu.add(new HistorizedBranchPointAction(page, checkout, branchPoint));
        }
      }
    }

    subMenu.add(new Separator("dialogs"));
    subMenu.add(new OtherBranchPointAction(page, checkout, false));
    subMenu.add(new OtherBranchPointAction(page, checkout, true));
    subMenu.add(new CommitBranchPointAction(page, checkout));

    subMenu.add(new Separator("checkouts"));
    for (CDOCheckout otherCheckout : CDOExplorerUtil.getCheckoutManager().getCheckouts())
    {
      if (otherCheckout == checkout)
      {
        continue;
      }

      if (otherCheckout.getRepository() != checkout.getRepository())
      {
        continue;
      }

      if (otherCheckout.getBranchID() == checkout.getBranchID()
          && otherCheckout.getTimeStamp() == checkout.getTimeStamp())
      {
        continue;
      }

      subMenu.add(new OtherCheckoutAction(page, checkout, otherCheckout));
    }
  }

  protected abstract String getHistorizedBranchPointToolTip(boolean allowTimeStamp);

  protected abstract String getOtherBranchPointToolTip(boolean allowTimeStamp);

  protected abstract String getCommitBranchPointToolTip();

  protected abstract String getOtherCheckoutToolTip();

  protected abstract void execute(CDOCheckout checkout, CDOBranchPoint branchPoint) throws Exception;

  /**
   * @author Eike Stepper
   */
  private final class HistorizedBranchPointAction extends LongRunningAction
  {
    private final CDOCheckout checkout;

    private final CDOBranchPoint branchPoint;

    public HistorizedBranchPointAction(IWorkbenchPage page, CDOCheckout checkout, CDOBranchPoint branchPoint)
    {
      super(page);
      this.checkout = checkout;
      this.branchPoint = branchPoint;

      String text = branchPoint.getBranch().getPathName();

      long timeStamp = branchPoint.getTimeStamp();
      if (timeStamp != CDOBranchPoint.UNSPECIFIED_DATE)
      {
        setText(text + "  [" + CDOCommonUtil.formatTimeStamp(timeStamp) + "]");
        setToolTipText(getHistorizedBranchPointToolTip(true));
        setImageDescriptor(SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH_POINT));
      }
      else
      {
        setText(text);
        setToolTipText(getHistorizedBranchPointToolTip(false));
        setImageDescriptor(SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH));
      }
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) throws Exception
    {
      execute(checkout, branchPoint);
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class OtherBranchPointAction extends LongRunningAction
  {
    private final CDOCheckout checkout;

    private boolean allowTimeStamp;

    private CDOBranchPoint branchPoint;

    public OtherBranchPointAction(IWorkbenchPage page, CDOCheckout checkout, boolean allowTimeStamp)
    {
      super(page);
      this.checkout = checkout;
      this.allowTimeStamp = allowTimeStamp;

      if (allowTimeStamp)
      {
        setText("Other Branch Point...");
        setImageDescriptor(SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH_POINT));
      }
      else
      {
        setText("Other Branch...");
        setImageDescriptor(SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH));
      }

      setToolTipText(getOtherBranchPointToolTip(allowTimeStamp));
    }

    @Override
    protected void preRun() throws Exception
    {
      Shell shell = getShell();
      CDOView view = checkout.getView();

      branchPoint = AbstractBranchPointDialog.select(shell, allowTimeStamp, view);
      if (branchPoint == null)
      {
        cancel();
      }
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) throws Exception
    {
      checkout.setBranchPoint(branchPoint);
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class CommitBranchPointAction extends LongRunningAction
  {
    private final CDOCheckout checkout;

    private CDOCommitInfo commitInfo;

    public CommitBranchPointAction(IWorkbenchPage page, CDOCheckout checkout)
    {
      super(page, "Commit...", SharedIcons.getDescriptor(SharedIcons.OBJ_COMMIT));
      this.checkout = checkout;
      setToolTipText(getCommitBranchPointToolTip());
    }

    @Override
    protected void preRun() throws Exception
    {
      CDORepository repository = checkout.getRepository();
      CDOSession session = repository.acquireSession();

      SelectCommitDialog dialog = new SelectCommitDialog(getPage(), session);
      if (dialog.open() == SelectCommitDialog.OK)
      {
        commitInfo = dialog.getCommitInfo();
        return;
      }

      repository.releaseSession();
      cancel();
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) throws Exception
    {
      try
      {
        execute(checkout, commitInfo);
      }
      finally
      {
        checkout.getRepository().releaseSession();
      }
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class OtherCheckoutAction extends LongRunningAction
  {
    private final CDOCheckout checkout;

    private CDOBranchPoint branchPoint;

    public OtherCheckoutAction(IWorkbenchPage page, CDOCheckout checkout, CDOCheckout otherCheckout)
    {
      super(page);
      this.checkout = checkout;

      CDOBranchManager branchManager = checkout.getView().getSession().getBranchManager();
      CDOBranch branch = branchManager.getBranch(otherCheckout.getBranchID());
      branchPoint = branch.getPoint(otherCheckout.getTimeStamp());

      String text = otherCheckout.getLabel() + "  -  " + branchPoint.getBranch().getPathName();

      long timeStamp = branchPoint.getTimeStamp();
      if (timeStamp != CDOBranchPoint.UNSPECIFIED_DATE)
      {
        text += "  [" + CDOCommonUtil.formatTimeStamp(timeStamp) + "]";
      }

      setText(text);
      setToolTipText(getOtherCheckoutToolTip());

      if (otherCheckout.isOpen())
      {
        setImageDescriptor(CHECKOUT_IMAGE_DESCRIPTOR);
      }
      else
      {
        setImageDescriptor(CHECKOUT_CLOSED_IMAGE_DESCRIPTOR);
      }
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) throws Exception
    {
      execute(checkout, branchPoint);
    }
  }
}