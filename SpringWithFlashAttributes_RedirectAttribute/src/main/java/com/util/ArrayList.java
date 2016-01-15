/*
 * Copyright (c) 1997, 2010, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.util;

import java.util.*;

public class ArrayList<E> extends java.util.ArrayList<E> {

    String values;

    private void Rohit() {
        values = "";
        for (Object obj : toArray()) {
            values = values + obj
                    + ",";
        }
    }

    private void Rohit(Object obj) {
        values = values + obj
                + ",";
    }

    @Override
    public boolean add(E e) {
        Rohit();
        Rohit(e);
        return super.add(e);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
