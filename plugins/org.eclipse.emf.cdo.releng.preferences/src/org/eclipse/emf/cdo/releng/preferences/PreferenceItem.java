/*
 * Copyright (c) 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.preferences;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Preference Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getRoot <em>Root</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getScope <em>Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getAbsolutePath <em>Absolute Path</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getScopeRelativePath <em>Scope Relative Path</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem()
 * @model abstract="true"
 * @generated
 */
public interface PreferenceItem extends EObject
{
  /**
   * Returns the value of the '<em><b>Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root</em>' reference.
   * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem_Root()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  PreferenceNode getRoot();

  /**
   * Returns the value of the '<em><b>Absolute Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Absolute Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Absolute Path</em>' attribute.
   * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem_AbsolutePath()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  String getAbsolutePath();

  /**
   * Returns the value of the '<em><b>Scope Relative Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scope Relative Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scope Relative Path</em>' attribute.
   * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem_ScopeRelativePath()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  String getScopeRelativePath();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.cdo.releng.preferences.PreferenceItem#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Scope</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scope</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scope</em>' reference.
   * @see org.eclipse.emf.cdo.releng.preferences.PreferencesPackage#getPreferenceItem_Scope()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  PreferenceNode getScope();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  PreferenceNode getParent();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  PreferenceItem getItem(String path);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  PreferenceItem getInScope(String scopeName);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  PreferenceItem getInScope();

} // PreferenceItem