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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonasferr
 */
public class KNN {

    // lista de todos os pontos
    private ArrayList<Point> listaPonto;

    /**
     * Construtor
     * @param listaPonto
     */
    public KNN(ArrayList<Point> listaPonto) {
        // guarda a lista de pontos
        this.listaPonto = listaPonto;
    }

    public KNN(Point... pontos) {

        this.listaPonto = new ArrayList<Point>();
        this.listaPonto.addAll(Arrays.asList(pontos));
    }

    // adiciona um ponto a lista
    public void adicionaPonto(Point pnt) {
        // se a lista ja foi criada
        if (listaPonto != null) {
            // adiciona o ponto
            listaPonto.add(pnt);
        }
    }

    /**
     * Calcula os k mais proximos do ponto passado 
     * @param p
     * @param k
     * @return
     */
    private ArrayList<Point> getKMaisProximos(Point p, int k) {

        ArrayList<Point> distances = new ArrayList<Point>();
        ArrayList<Point> kNearest = new ArrayList<Point>();

        for (Point point : this.listaPonto) {

            point.currentDistance = p.distance(point);
            distances.add(point);
        }

        PointSort pointSort = new PointSort();

        Collections.sort(distances, pointSort);

        for (int i = 0; i < k; i++) {

            kNearest.add(distances.get(i));
        }

        return kNearest;
    }

    /**
     * Classifica ponto desconhecido
     * @param pntDesconhecido
     * @return
     */
    public Integer classifica(Point pntDesconhecido, int k) {
        
        if (k > listaPonto.size()) {

            k = listaPonto.size();
        }

        Map<Integer, Integer> classes1 = new HashMap<Integer, Integer>();

        // obtem a lista de pontos mais proximos
        ArrayList<Point> kMaisProximos = getKMaisProximos(pntDesconhecido, k);
        // percorre k mais proximos
        for (Point p : kMaisProximos) {

            if (! classes1.containsKey(p.pointClass)){

                classes1.put(p.pointClass, 1);
            }

            else {

                int g = classes1.get(p.pointClass);
                classes1.put(p.pointClass, ++g);
            }
        }

        int classe = 0 , numClasse = 0;

        for (Integer i : classes1.keySet()){

            if (numClasse < classes1.get(i)) {

                numClasse = classes1.get(i);
                classe = i;
            }
        }

        return classe;

    }

    /**
     * Retorna lista de pontos
     * @return
     */
    public ArrayList<Point> getListaPonto() {
        return listaPonto;
    }

    /**
     * Configura Lista de pontos
     * @param listaPonto
     */
    public void setListaPonto(ArrayList<Point> listaPonto) {
        this.listaPonto = listaPonto;
    }

    public void clearPoints(){

        this.listaPonto.clear();
    }
}
