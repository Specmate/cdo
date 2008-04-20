/**
 * <copyright>
 * </copyright>
 *
 * $Id: Class1.java,v 1.1 2008-04-20 09:58:05 estepper Exp $
 */
package org.eclipse.emf.cdo.tests.model3;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.tests.model3.subpackage.Class2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Class1</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.cdo.tests.model3.Class1#getClass2 <em>Class2</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.cdo.tests.model3.Model3Package#getClass1()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Class1 extends CDOObject
{
  /**
   * Returns the value of the '<em><b>Class2</b></em>' reference list. The list contents are of type
   * {@link org.eclipse.emf.cdo.tests.model3.subpackage.Class2}. It is bidirectional and its opposite is '{@link org.eclipse.emf.cdo.tests.model3.subpackage.Class2#getClass1 <em>Class1</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class2</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Class2</em>' reference list.
   * @see org.eclipse.emf.cdo.tests.model3.Model3Package#getClass1_Class2()
   * @see org.eclipse.emf.cdo.tests.model3.subpackage.Class2#getClass1
   * @model opposite="class1"
   * @generated
   */
  EList<Class2> getClass2();

} // Class1
