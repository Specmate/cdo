/*
 * Copyright (c) 2009-2012, 2015, 2016 Eike Stepper (Loehne, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *
 *  Initial Publication:
 *    Eclipse Magazin - http://www.eclipse-magazin.de
 */
package org.gastro.inventory.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.gastro.inventory.util.InventoryAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this
 * factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters
 * also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class InventoryItemProviderAdapterFactory extends InventoryAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public InventoryItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Stock} instances. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected StockItemProvider stockItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Stock}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createStockAdapter()
  {
    if (stockItemProvider == null)
    {
      stockItemProvider = new StockItemProvider(this);
    }

    return stockItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.StockProduct} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected StockProductItemProvider stockProductItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.StockProduct}. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @generated
   */
  @Override
  public Adapter createStockProductAdapter()
  {
    if (stockProductItemProvider == null)
    {
      stockProductItemProvider = new StockProductItemProvider(this);
    }

    return stockProductItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Recipe} instances. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected RecipeItemProvider recipeItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Recipe}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createRecipeAdapter()
  {
    if (recipeItemProvider == null)
    {
      recipeItemProvider = new RecipeItemProvider(this);
    }

    return recipeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Ingredient} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected IngredientItemProvider ingredientItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Ingredient}. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @generated
   */
  @Override
  public Adapter createIngredientAdapter()
  {
    if (ingredientItemProvider == null)
    {
      ingredientItemProvider = new IngredientItemProvider(this);
    }

    return ingredientItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.MenuCard} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected MenuCardItemProvider menuCardItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.MenuCard}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createMenuCardAdapter()
  {
    if (menuCardItemProvider == null)
    {
      menuCardItemProvider = new MenuCardItemProvider(this);
    }

    return menuCardItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Restaurant} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected RestaurantItemProvider restaurantItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Restaurant}. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @generated
   */
  @Override
  public Adapter createRestaurantAdapter()
  {
    if (restaurantItemProvider == null)
    {
      restaurantItemProvider = new RestaurantItemProvider(this);
    }

    return restaurantItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Department} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected DepartmentItemProvider departmentItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Department}. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @generated
   */
  @Override
  public Adapter createDepartmentAdapter()
  {
    if (departmentItemProvider == null)
    {
      departmentItemProvider = new DepartmentItemProvider(this);
    }

    return departmentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Offering} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected OfferingItemProvider offeringItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Offering}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createOfferingAdapter()
  {
    if (offeringItemProvider == null)
    {
      offeringItemProvider = new OfferingItemProvider(this);
    }

    return offeringItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Table} instances. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected TableItemProvider tableItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Table}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createTableAdapter()
  {
    if (tableItemProvider == null)
    {
      tableItemProvider = new TableItemProvider(this);
    }

    return tableItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Employee} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected EmployeeItemProvider employeeItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Employee}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createEmployeeAdapter()
  {
    if (employeeItemProvider == null)
    {
      employeeItemProvider = new EmployeeItemProvider(this);
    }

    return employeeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.gastro.inventory.Section} instances. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected SectionItemProvider sectionItemProvider;

  /**
   * This creates an adapter for a {@link org.gastro.inventory.Section}. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter createSectionAdapter()
  {
    if (sectionItemProvider == null)
    {
      sectionItemProvider = new SectionItemProvider(this);
    }

    return sectionItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || ((Class<?>)type).isInstance(adapter))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public void dispose()
  {
    if (stockItemProvider != null)
    {
      stockItemProvider.dispose();
    }
    if (stockProductItemProvider != null)
    {
      stockProductItemProvider.dispose();
    }
    if (recipeItemProvider != null)
    {
      recipeItemProvider.dispose();
    }
    if (ingredientItemProvider != null)
    {
      ingredientItemProvider.dispose();
    }
    if (menuCardItemProvider != null)
    {
      menuCardItemProvider.dispose();
    }
    if (restaurantItemProvider != null)
    {
      restaurantItemProvider.dispose();
    }
    if (departmentItemProvider != null)
    {
      departmentItemProvider.dispose();
    }
    if (offeringItemProvider != null)
    {
      offeringItemProvider.dispose();
    }
    if (tableItemProvider != null)
    {
      tableItemProvider.dispose();
    }
    if (employeeItemProvider != null)
    {
      employeeItemProvider.dispose();
    }
    if (sectionItemProvider != null)
    {
      sectionItemProvider.dispose();
    }
  }

}
