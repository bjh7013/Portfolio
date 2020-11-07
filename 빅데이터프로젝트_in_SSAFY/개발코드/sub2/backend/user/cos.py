import os
import numpy as np
from numpy import dot
from numpy.linalg import norm
import matplotlib.pyplot as plt
import pandas as pd
import sklearn as sk

from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split

flavors = {
    'flavor_name' : ['spicy','sweet','sour','salty','oily','savory','bitter','mild','soft','chewy','dry','crispy'],
    'flavor' : [
        [1,3,4,4,5,1,3,3,3,4,1,3],
        [1,3,4,4,5,1,3,2,3,1,1,3],
        [2,6,8,8,10,2,6,6,6,8,2,6],
        [5,3,4,4,5,1,3,0,3,1,2,3],
        [3,4,5,1,5,1,3,0,3,2,1,3],
        [3,4,1,1,5,1,3,0,3,2,1,3],
    ],
    'user' : [1,2,3,4,5,6],
    'user_name' : ['jieun','0zoo','baek','tree','dragonwater','temps']
}

flavor_df = pd.DataFrame(flavors['flavor'], columns = flavors['flavor_name'])

flavor_df.head()

def cos_sim(A, B):
       return dot(A, B)/(norm(A)*norm(B))

cos = []
# for i in range(flavor_df.index.start,flavor_df.index.stop,1):
#     cos.append([])
for j in range(flavor_df.index.start,flavor_df.index.stop,1):
    # print(cos_sim(flavors['flavor'][i],flavors['flavor'][j]))
    cos.append(cos_sim(flavors['flavor'][2],flavors['flavor'][j]))
topid = sorted(range(len(cos)),key= lambda i: cos[i])[-3:]

print(topid)

[cos[i] for i in np.argsort(cos)[-3:]]
print([cos[i] for i in np.argsort(cos)[-3:]])
# 유저번호를 넣고 자기제외 상위 N명을 뽑는다 