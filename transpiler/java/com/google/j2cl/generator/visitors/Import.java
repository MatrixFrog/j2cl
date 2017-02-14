/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.generator.visitors;

import com.google.j2cl.ast.TypeDescriptor;

/**
 * A Node class that represents the goog.require statement:
 *
 * <pre>
 * {
 *   var ClassNameAlias = goog.require('gen.class.File.Name').
 * }
 * </pre>
 */
public class Import implements Comparable<Import>, Alias<TypeDescriptor> {

  private String alias;
  private TypeDescriptor typeDescriptor;

  Import(String alias, TypeDescriptor typeDescriptor) {
    this.alias = alias;
    this.typeDescriptor = typeDescriptor;
  }

  /** Returns the alias. */
  @Override
  public String getAlias() {
    return alias;
  }

  /**
   * Returns the importable module path for the class impl this may be different from the file path
   * in the case of JsTypes with a customized namespace.
   */
  public String getImplModulePath() {
    return typeDescriptor.getImplModuleName();
  }

  /**
   * Returns the importable module path for the class header this may be different from the file
   * path in the case of JsTypes with a customized namespace.
   */
  public String getHeaderModulePath() {
    return typeDescriptor.getModuleName();
  }

  /** Returns the associated type descriptor. */
  @Override
  public TypeDescriptor getElement() {
    return typeDescriptor;
  }

  /** Imported items should be sorted by module name first, and then class name. */
  @Override
  public int compareTo(Import that) {
    return this.getImplModulePath().compareTo(that.getImplModulePath());
  }

  @Override
  public String toString() {
    return alias + " => " + typeDescriptor;
  }
}
