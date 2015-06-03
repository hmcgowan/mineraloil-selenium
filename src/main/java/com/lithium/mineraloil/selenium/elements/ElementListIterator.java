package com.lithium.mineraloil.selenium.elements;

import org.openqa.selenium.NoSuchElementException;

import java.util.Iterator;

class ElementListIterator<T extends ElementList> implements Iterator {
    private ElementList elementList;
    private int index = 0;

    public ElementListIterator(T elements) {
        this.elementList = elements;
    }


    @Override
    public boolean hasNext() {
        return (elementList.size() > index) && (elementList != null);
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return elementList.get(index++);
        } else {
            throw new NoSuchElementException("There are no elements size=" + elementList.size());
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Unable to remove elements from the DOM!");
    }
}
