# 框架

### 链表：

```java
class ListNode{
    int val;
    ListNode next;
}

void traverse(ListNode head){
    for (ListNode p = head; p != null; p=p.next ){
        // 迭代访问 p.val
    }
}

void tracerse(ListNode head){
    // 递归访问
    tracerse(head.next);
}
```



### 二叉树

```java
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

void traverse(TreeNode root){
    // 前序遍历
    traverse(root.left);
    // 中序遍历
    traverse(root.right);
    // 后续遍历
}
```



### 二分查找

```

```



### N叉树

```java
class TreeNode{
    int val;
    TreeNode[] chidren;
}

void traverse(TreeNode root){
    for(TreeNode child:root.children){
        traverse(child);
    }
}
```



### BFS（最短距离

```java
int BFS(Node start,Node target){
    Queue<Node> q; // BFS暂存队列
    Set<Node> visited; // 已经处理过的节点
    q.push(start);
    visited.add(start);
    int step = 0;
    while(q not empty){
        int sz = q.size();
        for(int i=0; i<sz ; i++){
            Node cur = q.poll();
            // 重点判断
            if(cur is target){
                return step;
            }
            // 将当前节点的子节点都装入队列
            for(Node x:cur.adj){
                if(x not in visited){
                    q.push(x);
                    visited.add(x);
                }
            }
        }
        step++;
    }
}
```



### DFS、回溯

```java
result=[]
void  backtrack(path,list){
	if(满足条件){
        result.add(路径);
        return;
    }	
    for(选择 in 选择列表){
        做选择
        backtrack(path,list);
        撤销选择
    }
}
```

[1,2,3,4,null,null,5]