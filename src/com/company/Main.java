package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        if (graph.length == 0)
            return new ArrayList<>();
        int n = graph.length;
        int[] outDegree = new int[n];
        TreeSet<Integer> resSet = new TreeSet<>();
        Queue<Integer> list = new LinkedList<>();
        Map<Integer, List<Integer>> dstToSrc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                dstToSrc.putIfAbsent(neighbor, new ArrayList<>());
                dstToSrc.get(neighbor).add(i);
                outDegree[i]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0)
                list.offer(i);
        }
        while (!list.isEmpty()) {
            int curr = list.poll();
            resSet.add(curr);
            if (!dstToSrc.containsKey(curr))
                continue;
            for (int src : dstToSrc.get(curr)) {
                outDegree[src]--;
                if (outDegree[src] == 0)
                    list.offer(src);
            }
        }
        return new ArrayList<>(resSet);
    }

}