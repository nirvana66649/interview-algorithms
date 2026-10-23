# Interview Algorithms

按专题整理的算法面试复习笔记，以 Java 实现、思路解释、边界条件和复杂度分析为主。欢迎通过 Issue 纠错、认领题目，通过 Pull Request 补充题解。

## 当前内容

- 19 个专题目录，来自个人题库的 513 个原始条目；含跨专题重复与变体，不代表 513 道独立题。
- 3 篇原创整理的 Java 示范题解：子集、全排列、字母大小写排列。
- 其余条目暂为题目索引，尚未全部核验题名、来源或解法。索引状态不代表难度、个人掌握程度或公司面试频率。

## 从哪里开始

1. 查看 [专题目录](topics/README.md)，选择当前要练的专题。
2. 先独立写思路、代码和 TC/SC，再对照题解及易错点。
3. 发现错误先开 Issue；新增题解请按 [贡献指南](CONTRIBUTING.md) 提交 PR。

推荐入门顺序：数组与哈希 → 双指针与滑动窗口 → 二分 → 链表 → 栈与队列 → 树与图 → 回溯 → 动态规划。也可以按薄弱点复习。

## 已整理题解

| 题目 | 笔记 | Java |
|---|---|---|
| Subsets | [子集](solutions/backtracking/subsets.md) | [Subsets.java](java/src/algorithms/backtracking/Subsets.java) |
| Permutations | [全排列](solutions/backtracking/permutations.md) | [Permutations.java](java/src/algorithms/backtracking/Permutations.java) |
| Letter Case Permutation | [大小写排列](solutions/backtracking/letter-case-permutation.md) | [LetterCasePermutation.java](java/src/algorithms/backtracking/LetterCasePermutation.java) |

## 本地检查

需要 Python 3 和 JDK 17 或以上，无额外 Java 依赖。

```bash
python3 scripts/check_repository.py
bash scripts/test-java.sh
```

第一条检查索引、Markdown 相对链接和公开文件范围。第二条编译 Java 示例，并对照独立枚举结果检查空输入、重复调用、结果数量、重复结果和输入是否被修改。GitHub Actions 配置会运行这两项检查；本地整理并不代表远端 CI 已执行。

## 内容状态

| 状态 | 含义 |
|---|---|
| 索引待整理 | 有题名，尚待确认精确题意、原题链接和题解 |
| 已整理 | 有题意简述、原创解释、实现、复杂度与边界用例 |

学习进度属于个人信息，请在自己的副本中记录；公共状态只反映内容是否整理。

## 来源与使用

提供原题链接和自行撰写的简述，不批量转载第三方完整题面、官方题解、付费面经或课程材料。用户回忆和自定义变体需明确标注，不能作为特定公司的真实面试证据。参见 [来源说明](SOURCES.md)。

原创代码与说明按 [MIT License](LICENSE) 提供。第三方链接内容不因本仓库的许可证而获得重新授权。
