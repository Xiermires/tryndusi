package org.tryndusi.model.impl;

import org.tryndusi.model.Actor;
import org.tryndusi.model.ActorState;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;

public class Device implements Actor {

    private final String name;
    private final BoundingBox bbox;

    private ActorState state = ActorState.IDLE;

    public Device(String name, BoundingBox geometry) {
        this.name = name;
        this.bbox = geometry;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return bbox;
    }

    @Override
    public ActorState update(Move move) {
        return null;
    }

    @Override
    public ActorState getState() {
        return state;
    }

    @Override
    public void setState(ActorState newState) {
        state = newState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Device other = (Device) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Device [name=" + name + "]";
    }
}
