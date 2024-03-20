# Ford-Fulkerson Algorithm

## Summary
The DSA Spring 2024 midterm consists of choosing, exploring, and implementing a project. The algorithm I chose was the Ford-Fulkerson Algorithm. This is an algorithm to maximize a flow in a directed network graph, given a "source" and a "sink" node. 

I decided to implement the Ford-Fulkerson Algorithm and a variant of it, called the Edmond-Karps Algorithm in Kotlin while also developing a more mathematical understanding of it. 


## What is Maximum Flow

Given a network graph with weighted and directed edges, we often want to see how we can maximize the traffic flowing through the edges from one node to a destination node. We usually measure this 

## How the Algorithm Works

## Ford-Fulkerson VS Edmond Karps

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



 
