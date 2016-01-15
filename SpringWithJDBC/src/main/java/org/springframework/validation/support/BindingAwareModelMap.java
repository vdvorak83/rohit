/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.validation.support;

/**
 *
 * @author stevejobs
 */
import java.util.Map;
import java.util.Set;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;

public class BindingAwareModelMap extends ExtendedModelMap {

    String model = "";

    public void setModel() {
        model = "<p>";
        Map m = super.asMap();
        for (int i = 0; i < m.size(); i++) {
            model = model + "<span>" + m.keySet().toArray()[i] + " = " + m.get(m.keySet().toArray()[i]) + "</span>";
        }
        model = model + "</p>";
    }

    public void addComponentToModel(String attributeName, Object attributeValue) {
        model = model + attributeName + " = " + attributeValue;
    }

    @Override
    public ExtendedModelMap addAttribute(String attributeName, Object attributeValue) {
        setModel();
        addComponentToModel(attributeName, attributeValue);
        return super.addAttribute(attributeName, attributeValue);
    }
//    @Override
//    public String toString() {
//        return model;
//    }
}
