# Introduction to Merkle Tree

To simply put, Merkel Trees are essentially a tree data structure in which data is stored in the leaf nodes and non leaf nodes store hashes of data with each non-leaf node being the combined hash value of the two nodes below it.

Mathematically, it can be expressed as

![](https://miro.medium.com/max/944/1*8QaHU1UGOfJWLNPVoyMy0w.jpeg)

> Computing the value of each node in a Merkel Tree

# For example: 
## Given a list of alphabets, create a merkel tree from it.

The bottom most layer of the tree would contain all the letters as the leaf nodes.

![](https://miro.medium.com/max/1400/1*1Z7r4Gfjnl_wcMkDcFEWeg.jpeg)

> Lowest layer of the tree would contain the data in each node

The layer above contains its hash values.

![](https://miro.medium.com/max/1400/1*T75YmaGKr3Rkoc8FjDcxIA.jpeg)

> The layer above the leaf node has the hash values of leaf node data

The nodes in the layer after the second layer are contains the hash value of the child nodes. Generally we take two nodes from the second layer and combine them to form another node. We can take more than two nodes as well but binary merkel trees is the simplest of them all and increasing the degree of nodes only increase the computation and algorithms complexity.

If we have even number of nodes, we take 2 consecutive nodes and form the parent layer. But if we have odd number of nodes, we take two consecutive nodes until one is left to form the parent layer, and then we repeat the remaining node by copying the hash to the parent layer.

![](https://miro.medium.com/max/1400/1*pteLSEjj_AyxurpqPhN8bA.jpeg)

> Layer 3 has the hash of the values of the 2 consecutive nodes of layer 2 and in case we have odd nodes in a layer the last node is repeated

Similarly the fourth layer is formed using the values of the third layer.

![](https://miro.medium.com/max/1400/1*HcGy-UdgGvhg-mFG78VXEQ.jpeg)

> The fourth layer is formed by the hash of the values of the 2 consecutive nodes of layer 2

The final layer or the root of the Merkel Tree is formed by hash value of the last two nodes remaining in top most layer. In any case, odd or even leaf nodes, we will always have two nodes in the top most layer.

![](https://miro.medium.com/max/1400/1*KdTlPP6LQk_qlhzKP5V7OQ.jpeg)

> Merkel Tree formed by the five letters

# Verification

The importance Merkel Trees is in its ability to verify data with efficiency. Given any data from the list we can verify in O(h) time complexity that this data is valid or not. Moreover, we do not need the entire list for verification.

A much simpler form of Merkel Tree is a hash chain or simply a blockchain in which each node has the hash of the previous node’s value. If we tamper any node in between we can identify in ***O(n)*** time whether the node is tampered or not. Verification in hash chain can be performed by calculating the hash of all the nodes starting from the node in question and go till the end. In a situation where we have with multiple nodes to verify, we start with the node that is first among all the suspected nodes and calculate the hash of the last node from then on. Now that we have the hash of last node, we can compare and check if this hash matches or not. Hash chain seems simple but is not an efficient choice for large data objects. Since we need the entire chain physically present with us to verify the data, it makes hash chains space inefficient as well.

This is not the case with verification in Merkel Tree. To illustrate the verification process consider the example below.

***Suppose I received a data C from another server. Lets say this is C’. We want to verify C’ is not tampered. We have in out possession a merkel tree of all the data in the list.***

In case of a hash chain we would need the entire list of data to verify that C’ is correct. In Merkel Tree we only need the hashes. Following diagram illustrates how we can verify, C’ without other data objects available with us.

![](https://miro.medium.com/max/1256/1*HwZtuEwJVDvEJio4OOCKpw.jpeg)

> Verifying C’ by hashing all the nodes that lead us to the root


    1. Find the position of the C’ in the list. Probably by searching by id.
    2. Calculate the the hash of C’
    3. Calculate the value of the parent node by hashing the current node with its neighbor ( next if position is odd and previous if position in even) and set the parent as the current node.
    4. Repeat step 3 until we find the root
    5. Compare the root with the previous root, if they match then C’

Compare the new root with the existing root. If the new root matches then the C’ is essentially C and not tampered.

To verify a data in hash chain we need O(n) time since we would calculate n hashes in the worst case where as in case of Merkel Tree the same data can be verified in ***O(logn)*** time since we only calculate ***logn*** hashes.

## Creation

As already mentioned before, Merkel Tree are created by taking two nodes from each layer and hashing them to create the parent node. by representing the tree in matrix form we can mathematically write it as:

![](https://miro.medium.com/max/1400/1*EA7FYersQE_oS6SLBuxlNQ.jpeg)

***This makes the root of the tree available at tree[0][0]***

## Verification

Verification is a bottom-up approach where we start from the data, find its hash and calculate the parent and continue this until we find the root. Mathematically, we can express it as follows:

![](https://miro.medium.com/max/1400/1*gGD-kH3a1_CoYeHyYLeodA.jpeg)

# Use Cases :- 

    1. Git, a distributed version control system, is one of the most widely used. It is used to handle projects by programmers from all around the world.
    2. Interplanetary File System, a peer-to-peer distributed protocol, is another suitable implementation. It's also open-source, allowing computers to join and use a centralized file system.
    3. Amazon DynamoDB and Apache Cassandra use it during the data replication process. These No-SQL distributed databases use Merkle trees to control discrepancies.

