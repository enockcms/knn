/**
 * Copyright (c) 2010 Leonardo Nascimento Ferreira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package br.usp.icmc.knn;

import java.awt.Color;

/**
 *
 * @author leoferr
 */
public class Point {

    private java.awt.Point point;
    public int pointClass;
    public Color color;

    public double currentDistance;

    public static final int DEFAULT_CLASS = -1;

    public Point(java.awt.Point point, int pointClass, Color color){

        this.point = point;
        this.pointClass = pointClass;
        this.color = color;
        
    }

    public Point(java.awt.Point point){

        this(point, DEFAULT_CLASS, Color.BLACK);
    }

    public Point (int x, int y){
        
        this(new java.awt.Point(x,y), DEFAULT_CLASS, Color.BLACK);
    }

    public double distance(Point point){

        return this.point.distance(point.point);
    }

    public int getX(){

        return this.point.x;
    }

    public int getY(){

        return this.point.y;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Point){

            Point p = (Point) obj;

            return this.point.equals(p.point);
        }

        else {

            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.point != null ? this.point.hashCode() : 0);
        return hash;
    }

}
