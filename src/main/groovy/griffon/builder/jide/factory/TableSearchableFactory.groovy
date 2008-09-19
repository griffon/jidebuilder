/*
 * Copyright 2007-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.builder.jide.factory

import javax.swing.JTable
import com.jidesoft.swing.TableSearchable

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
class TableSearchableFactory extends AbstractJideComponentFactory implements DelegatingJideFactory {
   public static final String TABLE_SEARCHABLE = "_TABLE_SEARCHABLE_"

   public TableSearchableFactory() {
      super( TableSearchable )
   }

   public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map properties) throws InstantiationException, IllegalAccessException {
      FactoryBuilderSupport.checkValueIsNull(value, name)
      JTable table = properties.remove("table")
      if( !table ){
         table =  new JTable()
      }
      def searchable = new TableSearchable( table )
      builder.context[(TABLE_SEARCHABLE)] = searchable
      if( properties.id ){
         builder.setVariable( properties.id+"_searchable", searchable )
      }
      return table
   }

   public boolean onHandleNodeAttributes( FactoryBuilderSupport builder, Object node,
         Map attributes ) {
     setWidgetAttributes( builder, builder.context[(TABLE_SEARCHABLE)], attributes, true )
     return true
   }
}