/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.core.math;

import com.almasb.fxgl.core.pool.Poolable;
import javafx.geometry.Point2D;

import java.io.Serializable;

/**
 * A 2D column vector with float precision.
 * Can be used to represent a point in 2D space.
 * Can be used instead of JavaFX Point2D to avoid object allocations.
 * This is also preferred for private or scoped fields.
 *
 * Source: jbox2d.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public final class Vec2 implements Serializable, Poolable {
    private static final long serialVersionUID = 1L;

    public float x, y;

    public Vec2() {
        this(0, 0);
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Convenience ctor for double values.
     * Note: values will be typecast to float.
     *
     * @param x x component
     * @param y y component
     */
    public Vec2(double x, double y) {
        this((float) x, (float) y);
    }

    public Vec2(Vec2 toCopy) {
        this(toCopy.x, toCopy.y);
    }

    public Vec2(Point2D toCopy) {
        this(toCopy.getX(), toCopy.getY());
    }

    /**
     * Zero out this vector.
     */
    public void setZero() {
        x = 0.0f;
        y = 0.0f;
    }

    /**
     * Set this vector component-wise.
     *
     * @param x x component
     * @param y y component
     * @return this vector
     */
    public Vec2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Set this vector to another vector.
     *
     * @return this vector
     */
    public Vec2 set(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    /**
     * Set this vector to another vector.
     *
     * @return this vector
     */
    public Vec2 set(Point2D vector) {
        this.x = (float) vector.getX();
        this.y = (float) vector.getY();
        return this;
    }

    /**
     * Set this vector from angle.
     *
     * @param degrees angle in degrees
     * @return this vector
     */
    public Vec2 setFromAngle(double degrees) {
        this.x = FXGLMath.cosDeg((float)degrees);
        this.y = FXGLMath.sinDeg((float)degrees);
        return this;
    }

    /**
     * Return the sum of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 add(Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }

    /**
     * Return the sum of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 add(Point2D vector) {
        return add(vector.getX(), vector.getY());
    }

    /**
     * Return the sum of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 add(double otherX, double otherY) {
        return new Vec2(x + otherX, y + otherY);
    }

    /**
     * Return the difference of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 sub(Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }

    /**
     * Return the difference of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 sub(Point2D vector) {
        return sub(vector.getX(), vector.getY());
    }

    /**
     * Return the difference of this vector and another; does not alter either one.
     *
     * @return new vector
     */
    public Vec2 sub(double otherX, double otherY) {
        return new Vec2(x - otherX, y - otherY);
    }

    /**
     * Return this vector multiplied by a scalar; does not alter this vector.
     *
     * @return new vector
     */
    public Vec2 mul(double a) {
        return new Vec2(x * a, y * a);
    }

    /**
     * Return the negation of this vector; does not alter this vector.
     *
     * @return new vector
     */
    public Vec2 negate() {
        return new Vec2(-x, -y);
    }

    /**
     * Flip the vector and return it - alters this vector.
     *
     * @return this vector
     */
    public Vec2 negateLocal() {
        x = -x;
        y = -y;
        return this;
    }

    /**
     * Add another vector to this one and returns result - alters this vector.
     *
     * @return this vector
     */
    public Vec2 addLocal(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Adds values to this vector and returns result - alters this vector.
     *
     * @return this vector
     */
    public Vec2 addLocal(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Subtract another vector from this one and return result - alters this vector.
     *
     * @return this vector
     */
    public Vec2 subLocal(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /**
     * Subtract another vector from this one and return result - alters this vector.
     *
     * @return this vector
     */
    public Vec2 subLocal(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Multiply this vector by a number and return result - alters this vector.
     *
     * @return this vector
     */
    public Vec2 mulLocal(double a) {
        x *= a;
        y *= a;
        return this;
    }

    /**
     * Get the skew vector such that dot(skew_vec, other) == cross(vec, other).
     *
     * @return new vector
     */
    public Vec2 skew() {
        return new Vec2(-y, x);
    }

    /**
     * Get the skew vector such that dot(skew_vec, other) == cross(vec, other);
     * does not alter this vector.
     *
     * @param out the out vector to alter
     */
    public void skew(Vec2 out) {
        out.x = -y;
        out.y = x;
    }

    /**
     * @return the length of this vector
     */
    public float length() {
        return FXGLMath.sqrt(x * x + y * y);
    }

    /**
     * @return the squared length of this vector
     */
    public float lengthSquared() {
        return (x * x + y * y);
    }

    public double distance(Vec2 other) {
        return distance(other.x, other.y);
    }

    public double distance(Point2D other) {
        return distance(other.getX(), other.getY());
    }

    public double distance(double otherX, double otherY) {
        double dx = otherX - x;
        double dy = otherY - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distanceSquared(double otherX, double otherY) {
        double dx = otherX - x;
        double dy = otherY - y;
        return dx * dx + dy * dy;
    }

    public boolean distanceLessThanOrEqual(double otherX, double otherY, double distance) {
        return distanceSquared(otherX, otherY) <= distance * distance;
    }

    public boolean distanceGreaterThanOrEqual(double otherX, double otherY, double distance) {
        return distanceSquared(otherX, otherY) >= distance * distance;
    }

    /**
     * @return new normalized vector
     */
    public Vec2 normalizeVec() {
        float length = length();
        if (length < FXGLMath.EPSILON) {
            return new Vec2();
        }

        float invLength = 1.0f / length;
        return new Vec2(x * invLength, y * invLength);
    }

    /**
     * Normalize this vector and return the length before normalization.
     * Alters this vector.
     *
     * @return length before normalization
     */
    public float normalize() {
        float length = length();
        if (length < FXGLMath.EPSILON) {
            return 0f;
        }

        float invLength = 1.0f / length;
        x *= invLength;
        y *= invLength;
        return length;
    }

    /**
     * Normalizes and returns this vector.
     * Alters this vector.
     *
     * @return this vector
     */
    public Vec2 normalizeLocal() {
        normalize();
        return this;
    }

    public Vec2 midpoint(Vec2 other) {
        return new Vec2(
                x + (other.x - x) / 2,
                y + (other.y - y) / 2
        );
    }

    public Vec2 midpoint(Point2D other) {
        return new Vec2(
                x + (other.getX() - x) / 2,
                y + (other.getY() - y) / 2
        );
    }

    /**
     * True if the vector represents a pair of valid, non-infinite floating point numbers.
     */
    public boolean isValid() {
        return !Float.isNaN(x) && !Float.isInfinite(x) && !Float.isNaN(y) && !Float.isInfinite(y);
    }

    /**
     * @return new vector that has positive components
     */
    public Vec2 abs() {
        return new Vec2(FXGLMath.abs(x), FXGLMath.abs(y));
    }

    /**
     * Modify this vector to have only positive components.
     *
     * @return this vector
     */
    public Vec2 absLocal() {
        x = FXGLMath.abs(x);
        y = FXGLMath.abs(y);
        return this;
    }

    /**
     * @return angle in degrees (-180, 180] between this vector and X axis (1, 0)
     */
    public float angle() {
        return angle(1, 0);
    }

    /**
     * @param other other vector
     * @return angle in degrees (-180, 180] between this vector and other
     */
    public float angle(Vec2 other) {
        return angle(other.x, other.y);
    }

    /**
     * @param other other vector
     * @return angle in degrees (-180, 180] between this vector and other
     */
    public float angle(Point2D other) {
        return angle(other.getX(), other.getY());
    }

    /**
     * @param otherX x component of other vector
     * @param otherY y component of other vector
     * @return angle in degrees (-180, 180] between this vector and other
     */
    public float angle(double otherX, double otherY) {
        double angle1 = Math.toDegrees(Math.atan2(y, x));
        double angle2 = Math.toDegrees(Math.atan2(otherY, otherX));

        return (float) (angle1 - angle2);
    }

    /**
     * @return a copy of this vector
     */
    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Vec2 clone() {
        return new Vec2(x, y);
    }

    /**
     * @return a copy of this vector (new instance)
     */
    public Vec2 copy() {
        return clone();
    }

    /**
     * Note: object allocation.
     *
     * @return JavaFX Point2D representation
     */
    public Point2D toPoint2D() {
        return new Point2D(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public void reset() {
        setZero();
    }

    /* STATIC */

    public static Vec2 fromAngle(double degrees) {
        return new Vec2(FXGLMath.cosDeg((float)degrees), FXGLMath.sinDeg((float)degrees));
    }

    public static Vec2 abs(Vec2 a) {
        return new Vec2(FXGLMath.abs(a.x), FXGLMath.abs(a.y));
    }

    public static void absToOut(Vec2 a, Vec2 out) {
        out.x = FXGLMath.abs(a.x);
        out.y = FXGLMath.abs(a.y);
    }

    public static float dot(final Vec2 a, final Vec2 b) {
        return a.x * b.x + a.y * b.y;
    }

    public static float cross(final Vec2 a, final Vec2 b) {
        return a.x * b.y - a.y * b.x;
    }

    public static Vec2 cross(Vec2 a, float s) {
        return new Vec2(s * a.y, -s * a.x);
    }

    public static void crossToOut(Vec2 a, float s, Vec2 out) {
        final float tempy = -s * a.x;
        out.x = s * a.y;
        out.y = tempy;
    }

    public static void crossToOutUnsafe(Vec2 a, float s, Vec2 out) {
        out.x = s * a.y;
        out.y = -s * a.x;
    }

    public static Vec2 cross(float s, Vec2 a) {
        return new Vec2(-s * a.y, s * a.x);
    }

    public static void crossToOut(float s, Vec2 a, Vec2 out) {
        final float tempY = s * a.x;
        out.x = -s * a.y;
        out.y = tempY;
    }

    public static void crossToOutUnsafe(float s, Vec2 a, Vec2 out) {
        out.x = -s * a.y;
        out.y = s * a.x;
    }

    public static void negateToOut(Vec2 a, Vec2 out) {
        out.x = -a.x;
        out.y = -a.y;
    }

    public static Vec2 min(Vec2 a, Vec2 b) {
        return new Vec2(a.x < b.x ? a.x : b.x, a.y < b.y ? a.y : b.y);
    }

    public static Vec2 max(Vec2 a, Vec2 b) {
        return new Vec2(a.x > b.x ? a.x : b.x, a.y > b.y ? a.y : b.y);
    }

    public static void minToOut(Vec2 a, Vec2 b, Vec2 out) {
        out.x = a.x < b.x ? a.x : b.x;
        out.y = a.y < b.y ? a.y : b.y;
    }

    public static void maxToOut(Vec2 a, Vec2 b, Vec2 out) {
        out.x = a.x > b.x ? a.x : b.x;
        out.y = a.y > b.y ? a.y : b.y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj instanceof Vec2) {
            Vec2 other = (Vec2) obj;
            return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
                    && Float.floatToIntBits(y) == Float.floatToIntBits(other.y);
        }

        return false;
    }

    /**
     * @param p Point2D representation
     * @return true if vector equals Point2D given default error tolerance for float comparison
     */
    public boolean equalsPoint2D(Point2D p) {
        return FXGLMath.isEqual(x, (float) p.getX()) && FXGLMath.isEqual(y, (float) p.getY());
    }
}
