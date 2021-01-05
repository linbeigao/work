/*
 * Copyright 2021 Allen-Lin <allen.killer3724@gmail.com>.
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
 *
 * This project is inspired by my teacher Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * And I want you to know that everyone has the same feeling.
 * We suffering from depression,blue,sorrow,feeling painful and disappointed,getting anger.
 * It's ok. You are not alone. You are a good person.
 */
package art.antsomg.final_project;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import art.antsomg.final_project.x.Graph_X;
import art.antsomg.final_project.x.Vertex_X;
import art.antsomg.final_project.y.Graph_Y;
import art.antsomg.final_project.y.Vertex_Y;
import art.antsomg.final_project.z.Graph_Z;
import art.antsomg.final_project.z.Vertex_Z;
import tech.metacontext.ocnhfa.antsomg.impl.StandardEdge;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;
import tech.metacontext.ocnhfa.antsomg.model.AntsOMGSystem;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class HarassmentSystem implements AntsOMGSystem<HarassmentCurve> {

    int ant_population;
    Map<String, StandardGraph> graphs;
    List<HarassmentCurve> ants;
    double pheromone_deposit = 0.5;
    double explore_chance = 0.12;
    double evaporate_rate = 0.002;
    double alpha = 1.2, beta = 1.5, gamma=0.7;

    public HarassmentSystem(int ant_population) {

        this.ant_population = ant_population;
    }

    @Override
    public void init_graphs() {

        this.graphs = Map.of(
                "x", new Graph_X(alpha, beta),
                "y", new Graph_Y(alpha, beta),
                "z", new Graph_Z(gamma, beta)
        );
    }

    Graph_X getX() {

        return (Graph_X) this.graphs.get("x");
    }

    Graph_Y getY() {

        return (Graph_Y) this.graphs.get("y");
    }
    
     Graph_Z getZ() {

        return (Graph_Z) this.graphs.get("z");
    }
    
    @Override
    public void init_population() {

        this.ants = (List<HarassmentCurve>) Stream.generate(()
                -> new HarassmentCurve(
                        getX().getStart(),
                        getY().getStart(),
                        getZ().getStart()))
                .limit(ant_population)
                .collect(Collectors.toList());
    }

    @Override
    public void navigate() {

        this.ants.stream().forEach(ant -> {
            if (!ant.isCompleted()) {
                var trace = ant.getCurrentTrace();
                var x = getX().move((Vertex_X) trace.getDimension("x"),
                        this.pheromone_deposit, this.explore_chance);
                var y = getY().move((Vertex_Y) trace.getDimension("y"),
                        this.pheromone_deposit, this.explore_chance);
                var z = getZ().move((Vertex_Z) trace.getDimension("z"),
                        this.pheromone_deposit, this.explore_chance);
                var new_trace = new HarassmentTrace(x, y, z);
                ant.setCurrentTrace(new_trace);
                if (ant.isBalanced()) {
                    ant.addCurrentTraceToRoute();
                    ant.setCompleted(true);
                }
            }
        });
        this.evaporate();
    }

    @Override
    public void evaporate() {

        this.graphs.values().stream()
                .map(StandardGraph::getEdges)
                .flatMap(List<StandardEdge>::stream)
                .forEach(edge -> edge.evaporate(evaporate_rate));

    }

    @Override
    public boolean isAimAchieved() {

        return this.ants.stream().allMatch(HarassmentCurve::isCompleted);
    }

    @Override
    public List<HarassmentCurve> getAnts() {

        return this.ants;
    }

    @Override
    public Map<String, StandardGraph> getGraphs() {

        return this.graphs;
    }
}
