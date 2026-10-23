# 全排列 Permutations

原题：[LeetCode 46](https://leetcode.com/problems/permutations/)。以下是自行撰写的简述，完整约束以原题为准。

给互不相同的整数生成每一种排列。本实现不修改输入；空数组产生一个空排列，null 输入拒绝。

## 思路

每一层决定排列中的下一个位置。`used[i]` 表示输入位置 i 是否已经出现在当前路径中，只能选择尚未使用的位置。当路径长度等于 n 时，复制保存结果。

递归返回后，要同时移除路径末尾元素并清除 used 标记。这里记录的是当前路径的使用情况，不能把某次递归中用过的元素永久禁用。

## 实现与复杂度

[Java 源码](../../java/src/algorithms/backtracking/Permutations.java)

令 n 为输入长度。n 个互异元素有 n! 个排列，复制输出需要 O(n × n!) 时间；本实现的逐层扫描也在这个渐近界内。辅助空间 O(n)，输出空间 O(n × n!)。空输入单独为 O(1)。

## 边界与验证

- 空数组和单元素。
- 多次调用不应保留上次结果。
- 输入中含重复值需要 Permutations II 的去重规则。
- Java 测试使用字典序 next-permutation 枚举作独立对照，而非复写递归流程。
