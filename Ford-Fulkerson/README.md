# Ford-Fulkerson Algorithm

## Summary
The DSA Spring 2024 midterm consists of choosing, exploring, and implementing a project. The algorithm I chose was the Ford-Fulkerson Algorithm. This is an algorithm to maximize a flow in a directed network graph, given a "source" and a "sink" node. 

I decided to implement the Ford-Fulkerson Algorithm and a variant of it, called the Edmond-Karps Algorithm in Kotlin while also developing a more mathematical understanding of it. 


## What is Maximum Flow

Given a network graph with weighted and directed edges, we often want to see how we can maximize the traffic flowing through the edges from one node to a destination node. Usually, the "weight" of each edge is attributed as the capacity of the edge, or the maximum amount of traffic the edge can handle. The "flow" of the edge is the actual amount of traffic that is being transmitted at a given point. 

Two constraints are that no edge can have a flow that exceeds its capacity and that the flow entering and exiting an edge must be the same. 

## How the Algorithm Works

Superficially, the algorithm is fairly simple. Given a graph and initial flow of all edges being 0, here are the steps for how the Ford-Fulkerson Algorithm works. 

1. Find an augmenting path from the source to the sink. You can use any type of path-finding algorithm to find an augmenting path. The only condition is that all edges used in this path cannot have a flow that is equal to their capacity.
2. For each edge in the path, calculate the difference of its capacity and flow. Then find the minimum value between all the edges.
3. Update the flow of each edge in the path by adding this minimum value to the current flow. Also, update each edge's residual edge (backward edge). 
4. Redo 1-4 until we cannot find any more augmenting paths. Then, sum all the minimum values we calculated. That is our maximum flow.

Breaking down this algorithm, there are a couple things to take into note. 

One key aspect is the idea of "backward edges", or residual edges. Basically, for every edge that exists in the graph, we create another edge that is backwards with a capacity of 0. This allows us to "undo" any bad previous decisions made by the algorithm and redirect flow along a different path. In this implementation, I automatically generate backward edges whenever I add a new edge to a graph that will be used explicitly for the algorithm. Every time I update the flow of an edge, I also update the flow of the backward edge by as a negative value. The main reason why it's negative is because the flow must always be smaller than the capacity, so the value being negative does not break this constraint. However, conceptually, the flow of the backward edge matches the flow of the actual edge. The algorithm can also choose to use a backward edge as part of an augmenting path, but the actual effect of this is that the flow will get re-directed. 

## Ford-Fulkerson VS Edmond Karps

The Ford-Fulkerson Algorithm does not specify what algorithm we use when we find an augmenting path. By default, I chose DFS for my generic Ford-Fulkerson Algorithm. One variant of the FFA is the Edmond Karps algorithm, which is the exact same process-wise, except that it uses BFS to find it's augmenting paths. 

## Implementation

As part of the implementation, I developed several different classes and functions to help with the organization of this project

### Code Organization
1. `GraphDW` is a class that handles generating a network graph. This class has an adjacency list for each node (since it is directed). Each edge holds 2 attributes that are located in a Pair object. This is the `flow` and the `capacity` of the edge. The `flow` of the edge is the current amount of data/flow that is going through the node. The `capacity` of the edge is the maximum flow that can go through the node. At no point should the flow be larger than the capacity of the node. This class lives in the `Graph.kt` file. 

2. `GraphTraversal` is a class that holds the `bfs()` and `dfs()` functions that perform Breadth First Search and Depth First Search. I have two other classes, `MyQueue` and `MyStack` which are queues and stacks that are used to perform bfs and dfs. I also have a MinHeap that is used for these searching algorithms. These live in the `Searching.kt` and `MinHeap` files.

3. I have two functions,`fordFulkerson()` and `EdmondKarps()` in the `ford-fulkerson.kt` file that perform these two maximum flow algorithms utilizing all the classes and functions briefly explained above.

### Testing

To test that my algorithms work, I have a test file where I generated different graphs and ran my algorithms to see if they came to the right maximum flow value. These graphs (And their respective maximum flow values) were generated online or by hand and manually checked to make sure that it was correct. In that regard, it was very difficult to generate complex graphs due to the sheer manually need of writing each edge connection. This is also the main reason why benchmarking was not implememented as hand generating massive networks would be extremely irritating. 


## Conclusions

Overall, I'd definitely say that this was a fun but slightly unfortunate algorithm. It has a lot of mathematical depth that did not get covered and  isn't even neccessary to implement it. Due to the sheer manual labour required to generate graphs with numerous edges and nodes, I also couldn't manually test the time complexity and had to accept the mathematical proof for why BFS is better than DFS in this scenario. 



 
