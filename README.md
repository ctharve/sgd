Stochastic gradient descent implementation in Java.   

## 1. Stochastic Gradient Descent
This implementation is for predctiing click through rates given an advertisemnt, it's atributes, and some user information. 
The union of words across all advertisements are treated as a bag of words (BOW). This results in a feature space with dimension 
on the order of the number of unique words across all ads. If the large feature space results in a model that is over-fit, it 
could diminish our ability to predict click-through with new data. Further, the feature vector for each observation will be 
large and sparse, so it would be a computational benefit to reduce it's dimension. 

After implementing gradient descent to train a logisitc regression model, we will also implement L2 regularization with lazy
updating, and a hashing trick to reduce the dimension of the BOW features. 

<!-- links -->
[Homework 1]:http://courses.cs.washington.edu/courses/cse547/14wi/homework.html
