import jwt
import math
from backend.settings import SECRET_KEY
from django.shortcuts import render
from django.shortcuts import get_object_or_404
from django.db.models import Subquery, Q

from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework import status, permissions, viewsets
from rest_framework.serializers import ListSerializer


from .models import Food, User, Complist
from .serializers import FoodSerializer, UserSerializer, ComplistSerializer

from drf_yasg import openapi
from drf_yasg.utils import swagger_auto_schema

from .models import Diethistory, Complist, Flavor, Flavorpref, Food, Foodflavor, Foodnutrient, Fooddist
from .models import Foodpref, Preftest, Preftestdetail, User, Review
from .serializers import UserSerializer, DiethistorySerializer, ComplistSerializer, FlavorSerializer, FlavorprefSerializer, FoodSerializer, FoodflavorSerializer
from .serializers import FoodprefSerializer, FoodnutrientSerializer, PreftestSerializer, PreftestdetailSerializer, ReviewSerializer, FooddetailSerializer, FooddistSerializer
import datetime

import os
import numpy as np
from numpy import dot
from numpy.linalg import norm
# import matplotlib.pyplot as plt
import pandas as pd
import sklearn as sk

from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split



recommend_food = {}

# 회원가입
@api_view(['POST'])
def id_check(request):
    userId = request.data['user_id']
    try:
        User.objects.get(user_id=userId)
        return Response({'result': 'duplicated'})
    except:
        return Response({'result': 'useable'})

@api_view(['POST'])
def join(request):
    serializer = UserSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save()
        return Response(serializer.data)

# 로그인
@api_view(['POST'])
def login(request):
    userId = request.data['user_id']
    userPw = request.data['user_pw']


    if User.objects.filter(user_id=userId, user_pw=userPw).exists():
        loginUser = User.objects.get(user_id=userId)
        # 토큰생성
        jwt_token = jwt.encode({'user_id':loginUser.user_id, 'user_no': loginUser.user_no}, SECRET_KEY, algorithm='HS256').decode('utf-8')

        setRecommendFood(loginUser.user_no)

        return Response({'token':jwt_token, 'userId': userId})
    else:
        return Response({'result': '아이디와 비밀번호가 틀립니다.'})


@api_view(['GET'])
def getReco(request):
    global recommend_food
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)

    if user_no not in recommend_food:
        setRecommendFood(user_no)


    result = []
    for food in recommend_food[user_no]:
        temp = {}
        temp['food_no'] = food.food_no
        temp['food_name'] = food.food_name
        temp['cal'] = food.cal
        temp['manufacturer'] = food.manufacturer
        temp['amount'] = food.amount
        
        food_nutrient = FoodnutrientSerializer(food.foodnutrient_set.all(),many=True)
        
        nutri_list = []

        for fn in food_nutrient.data:
            nutri_list.append({"nutrient" : fn['nutrient'], "content" : fn['content']})

        temp['nutrient'] = nutri_list
        
        try:
            complist = Complist.objects.get(food=food.food_no, user=user_no)
            inComplist = True
        except Complist.DoesNotExist:
            inComplist = False
        
        temp['inComplist'] = inComplist
        
        result.append(temp)
    
    

    return Response(result)



# 마이페이지
@api_view(['GET', 'PUT', 'DELETE'])
def mypage(request):
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)

    user = get_object_or_404(User, user_no=user_no)

    if request.method == 'GET':
        serializer = UserSerializer(user)
        return Response(serializer.data)
    elif request.method == 'PUT':
        serializer = UserSerializer(user, data=request.data, partial=True)
        if(serializer.is_valid(raise_exception=True)):
            serializer.save()
        return Response(serializer.data)
    elif request.method == 'DELETE':
        user.delete()
        return Response("succcess")

class FoodModelViewSet(viewsets.ModelViewSet):
    queryset = Food.objects.all()
    serializer_class = FoodSerializer
    param_hello_hint = openapi.Parameter(
        'hello',
        openapi.IN_QUERY,
        description='this is a description of hello.',
        type=openapi.TYPE_STRING
    )
    # @swagger_auto_schema(manual_parmeters=[param_hello_hint],
    #     responses={200: FoodSerializer(many=True)})

# rest framework
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def food_list(request):
    foods = Food.objects.all()
    serializer = FoodSerializer(foods, many=True)
    return Response(serializer.data)

@api_view(['POST'])
@permission_classes([permissions.AllowAny])
def create(request):
    serializer = FoodSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save(user=request.user)
        return Response(serializer.data)
    
# 음식 맛 수정
@api_view(['PUT'])
def food_update(request, id):
    data = request.data
    if request.method == 'PUT':
        try:
            food = Food.objects.get(pk=id)
            serializer = FoodSerializer(food, data=data, partial=True)
            if(serializer.is_valid(raise_exception=True)):
                serializer.save()
            return Response(serializer.data)
        except Exception as e:
            return Response(status=status.HTTP_400_BAD_REQUEST)

# 리뷰
@api_view(['GET', 'POST', 'PUT', 'DELETE'])
def review(request, id=None):
    data = request.data

    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)
    
    if(request.method != 'DELETE'):
        data['user_no'] = user_no

    if(id == None):
        if request.method == 'GET': #리스트 조회
            review = Review.objects.filter(user = user_no)
            serializer = ReviewSerializer(review, many=True)
            return Response(serializer.data)
            
        if request.method == 'POST':
            data['food_no'] = Food.objects.get(food_no=data['food_no']).group.food_no
            serializer = ReviewSerializer(data = data)

            if(serializer.is_valid(raise_exception=True)):
                serializer.save()
                return Response(serializer.data)

    if request.method == 'PUT':
        try:
            review = Review.objects.get(pk=id)
            serializer = ReviewSerializer(review, data=data, partial=True)
            if(serializer.is_valid(raise_exception=True)):
                serializer.save()
            return Response(serializer.data)
        except Exception as e:
            print(e)
            return Response(status=status.HTTP_400_BAD_REQUEST)

    if request.method == 'DELETE':
        try:
            review = Review.objects.get(pk=id)
            print(type(review))
            review.delete()
            return Response("succcess")
        except Exception as e:
            print(e)
            return Response("fail")
    return Response("dddd")

@api_view(['POST'])
def preftest(request):
    #jwt 검증
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)
    
    print('-------------------------------------------------------------------------------')
    test_list = []
    for k in request.data.keys():
        print(k)
        test_list.append({'food_no': k, 'score' : request.data[k]})

    print(test_list)

    testSerializer = PreftestSerializer(data = {'user_no':user_no, 'test_details' : test_list})
    if(testSerializer.is_valid(raise_exception=True)):
        preftest = testSerializer.save()
        print(preftest.test_no)

    # PreftestdetailSerializer
    return Response(status=status.HTTP_201_CREATED)

# 식사데이터
@api_view(['GET', 'POST', 'PUT', 'DELETE'])
def diethistory(request, id=None, startdate=None, enddate=None):
    #jwt로부터 user_no 획득
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)

    #Path Variable에 id가 안붙는 경우 - List조회 / Post로 List에 항목 삽입
    if id == None:# 
        if request.method == 'GET': #리스트 조회
            history = Diethistory.objects.filter(user = user_no)
            serializer = DiethistorySerializer(history, many=True)
            return Response(serializer.data)

        if request.method == 'POST':
            request.data['user_no'] = user_no
            serializer = DiethistorySerializer(data = request.data)

            if(serializer.is_valid(raise_exception=True)):
                print(serializer.validated_data)
                serializer.save()
                return Response(serializer.data)
                
    else: ##PathVariable id가 입력되었을 때
        if request.method == 'GET':
            if startdate == None:
                try:
                    history = Diethistory.objects.get(pk=id)
                except Exception as e:
                    return Response(status=status.HTTP_400_BAD_REQUEST)

                serializer = DiethistorySerializer(history)
                res = serializer.data
                nutriSerialzier = FoodnutrientSerializer(history.food.foodnutrient_set.all(),many=True)
                print(nutriSerialzier.data)
                res['nutrient'] = nutriSerialzier.data 
                return Response(res)
                
        if request.method == 'PUT':
            try:
                history = Diethistory.objects.get(pk=id)
                serializer = DiethistorySerializer(history,data=request.data, partial=True)
                # print(serializer.is_valid())
                # print(serializer.validated_data)
                if(serializer.is_valid(raise_exception=True)):
                    serializer.save()
                return Response(serializer.data)
            except Exception as e:
                print(e)
                return Response(status=status.HTTP_400_BAD_REQUEST)
        if request.method == 'DELETE':
            try:
                history = Diethistory.objects.get(pk=id)
                history.delete()
                return Response("succcess")
            except:
                return Response("fail")


# 식사데이터
@api_view(['GET'])
def diethistory2(request, startdate, enddate):

    #jwt로부터 user_no 획득
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)

    try:
        start = datetime.date(int(startdate/10000), int((startdate%10000)/100), int(startdate%100))
        print(start)
        end = datetime.date(int(enddate/10000), int((enddate%10000)/100), int(enddate%100))
        print(end)
        q = Q(Q(user = user_no) & Q(eat_date__range=(start,end)))
        history = Diethistory.objects.filter(q)
    except Exception as e:
        return Response(status=status.HTTP_400_BAD_REQUEST)
    
    print(str(history.query))

    serializer = DiethistorySerializer(history, many=True)
    return Response(serializer.data)


# 비교리스트 추가 or 삭제
@api_view(['POST'])
def compitemC(request):
    #jwt 검증
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)
    
    try:
        complist = Complist.objects.get(food=request.data['food_no'], user=user_no)
        complist.delete()
        return Response("delete")
    except Complist.DoesNotExist:
        request.data['user_no'] = user_no
        serializer = ComplistSerializer(data = request.data)
        if(serializer.is_valid(raise_exception=True)):
            serializer.save()
            return Response(serializer.data)
        

# 비교리스트 조회
@api_view(['GET'])
def getComplist(request):
    #jwt 검증
    try:
        user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
    except Exception as e:
        return Response(status=status.HTTP_401_UNAUTHORIZED)

    try:
        complist = Complist.objects.filter(user = user_no)
    except Exception as e:
        return Response(status=status.HTTP_400_BAD_REQUEST)
    serializer = ComplistSerializer(complist, many=True)
    return Response(serializer.data)

# 비교리스트 삭제
@api_view(['DELETE'])
def compitemD(request, id):
    try:
        compitem = Complist.objects.get(pk=id)
    except:
        return Response(status=status.HTTP_400_BAD_REQUEST)
    # print(compitem)
    # print(type(compitem))
    compitem.delete()
    return Response(status=status.HTTP_200_OK)

# 이름으로(포함) 음식리스트 조회
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def namefood_list(request,food_name):
    food = Food.objects.filter(food_name__icontains=food_name, food_type='general')
    serializer = FoodSerializer(food, many=True)
    
    res = serializer.data
    # print(res)
    user_no = None

    if 'authorization' in request.headers:
        try:
            user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
        except Exception as e:
            return Response(status=status.HTTP_401_UNAUTHORIZED)
    
    for item in res:
        # print(type(item))
        # print(item['food_no'])
        nutriSerialzier = FoodnutrientSerializer(Food.objects.get(pk=item['food_no']).foodnutrient_set.all(),many=True)
        item['nutrient'] = nutriSerialzier.data
        
        if user_no:
            try:
                complist = Complist.objects.get(food=item['food_no'], user=user_no)
                inComplist = True
            except Complist.DoesNotExist:
                inComplist = False
            item['inComplist'] = inComplist        
        
    # print(res)
    return Response(res)

#이름으로 음식리스트(외식, 인스턴트 품목) 조회
@api_view(['GET'])
def searchsubfood(request, food_name):
    food = Food.objects.filter(food_name__icontains=food_name).exclude(food_type = 'general')
    serializer = FoodSerializer(food, many=True)
    
    res = serializer.data
    user_no = None
    if 'authorization' in request.headers:
        try:
            user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
        except Exception as e:
            return Response(status=status.HTTP_401_UNAUTHORIZED)

    for item in res:
        nutriSerialzier = FoodnutrientSerializer(Food.objects.get(pk=item['food_no']).foodnutrient_set.all(),many=True)
        item['nutrient'] = nutriSerialzier.data 
        if user_no:
            try:
                complist = Complist.objects.get(food=item['food_no'], user=user_no)
                inComplist = True
            except Complist.DoesNotExist:
                inComplist = False
            item['inComplist'] = inComplist        

    return Response(res)    

@api_view(['GET'])
def get_subfood_list(request, food_no):
    food = Food.objects.filter(group=food_no).exclude(food_type='general')
    serializer = FoodSerializer(food, many=True)

    res = serializer.data
    user_no = None
    if 'authorization' in request.headers:
        try:
            user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
        except Exception as e:
            return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        

    for item in res:
        nutriSerialzier = FoodnutrientSerializer(Food.objects.get(pk=item['food_no']).foodnutrient_set.all(),many=True)
        item['nutrient'] = nutriSerialzier.data 
        if user_no:
            try:
                complist = Complist.objects.get(food=item['food_no'], user=user_no)
                inComplist = True
            except Complist.DoesNotExist:
                inComplist = False
            item['inComplist'] = inComplist        
    
    return Response(res)
        
# 음식 상세조회(음식정보 + 영양성분리스트 + 음식점리스트 + 맛)
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def food_detail(request,food_no):
    # Food에서 food_no로 * 검색 | store_id 추출 => Store
    # Foodnutrient에서 food_no로 nutrient, content select
    # Foodflavor에서 flavor_no 추출 => Flavor에서 flavor_no로 검색
    food = Food.objects.filter(food_no=food_no)
    # store = Store.objects.filter(store_no__in=Subquery(food.values('store_id')))
    foodnutrient = Foodnutrient.objects.filter(food_id__in=Subquery(food.values('food_no'))).values('nutrient','content')
    foodflavor = Foodflavor.objects.filter(food_id=food_no)
    flavor = Flavor.objects.filter(flavor_no__in=Subquery(foodflavor.values('flavor_id')))
    
    food_serializer = FoodSerializer(food, many=True)
    foodnutrient_serializer = FoodnutrientSerializer(foodnutrient, many=True)
    # foodflavor_serializer = FoodflavorSerializer(foodflavor, many=True)
    flavor_serializer = FlavorSerializer(flavor, many=True)
    
    res = {}
    res = food_serializer.data[0]
    print(res)
    print(foodnutrient_serializer.data)
    print(flavor_serializer.data)
    res['nutrient'] = foodnutrient_serializer.data
    res['flavor'] = flavor_serializer.data

    user_no = None
    if 'authorization' in request.headers:
        try:
            user_no = jwt.decode(request.headers['authorization'], SECRET_KEY, algorithm='HS256')['user_no']
        except Exception as e:
            return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        
        try:
            complist = Complist.objects.get(food=food_no, user=user_no)
            inComplist = True
        except Complist.DoesNotExist:
            inComplist = False
        res['inComplist'] = inComplist        
        
    # food store foodnutrient flavor 
    # fooddetail = Food.objects.raw("
    #                 SELECT *
    #                 FROM food
    #                 WHERE food.food_no = food_no
    #             ")
    return Response(res)

# 음식으로 영양성분조회
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def foodnutrient_list(request,food_no):
    food = Food.objects.filter(food_no=food_no)
    foodnutrient = Foodnutrient.objects.filter(food_id__in=Subquery(food.values('food_no'))).values('nutrient', 'content')
    print(str(foodnutrient.query))
    serializer = FoodnutrientSerializer(foodnutrient, many=True)
    return Response(serializer.data)

# 영양성분으로 음식조회
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def nutrientfood_list(request,nut1,val1,nut2,val2):

    q1 = Q(Q(nutrient=nut1) & Q(content__lte=val1))
    q2 = Q(Q(nutrient=nut2) & Q(content__lte=val2))

    nutrient1 = Foodnutrient.objects.filter(q1)
    nutrient2 = Foodnutrient.objects.filter(q2)
    foodnutrient1 = Foodnutrient.objects.filter(food_id__in=Subquery(nutrient1.values('food_id')))
    foodnutrient2 = Foodnutrient.objects.filter(food_id__in=Subquery(nutrient2.values('food_id')))
    foodnutrient = Foodnutrient.objects.filter(food_id__in=Subquery(foodnutrient1.values('food_id') & foodnutrient2.values('food_id')))
    # print(str(nutrient2.query))
    # print(str(foodnutrient.query))
    # foodnutrient = Foodnutrient.objects.filter(q)
    serializer = FoodnutrientSerializer(foodnutrient, many=True)
    return Response(serializer.data)

# @api_view(['GET', 'DELETE'])
# def food_detail(request, pk):
#     food = get_object_or_404(Food, pk=pk)
#     if request.method == 'GET':
#         serializer = FoodSerializer(food)
#         return Response(serializer.data)


# 음식으로 식당리스트 조회
# @api_view(['GET'])
# @permission_classes([permissions.AllowAny])
# def store_list(request, food_no):
#     food = Food.objects.filter(food_no=food_no)
#     store = Store.objects.filter(store_no__in=Subquery(food.values('store_id')))
#     serializer = StoreSerializer(store, many=True)
#     return Response(serializer.data)

# 식당 상세조회
# @api_view(['GET'])
# @permission_classes([permissions.AllowAny])
# def store_detail(request, store_no):
#     store = Store.objects.filter(store_no=store_no)
#     serializer = StoreSerializer(store, many=True)
#     return Response(serializer.data)

# 카테고리에 포함되는 식당리스트 조회
# @api_view(['GET'])
# @permission_classes([permissions.AllowAny])
# def categorystore_list(request, category):
#     storecategory = Storecategory.objects.filter(category=category)
#     serializer = StorecategorySerializer(storecategory, many=True)
#     return Response(serializer.data)

# 맛으로 음식조회
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def flavorfood_list(request, flavor_name):
    # Entry.objects.all()[5:10] 6~10
    flavor = Flavor.objects.filter(flavor_name=flavor_name)
    foodflavor = Foodflavor.objects.filter(flavor_id__in=Subquery(flavor.values('flavor_no')))
    serializer = FoodflavorSerializer(foodflavor, many=True)
    return Response(serializer.data)

# 음식으로 맛조회
@api_view(['GET'])
@permission_classes([permissions.AllowAny])
def foodflavor_list(request, food_no):
    foodflavor = Foodflavor.objects.filter(food=food_no).select_related("flavor")
    serializer = FoodflavorSerializer(foodflavor, many=True)
    
    return Response(serializer.data)

# 연관음식 조회
@api_view(['GET'])
def get_realted_food(request, food_no):
    fooddist = Fooddist.objects.filter(foodx=food_no).exclude(dist=0).order_by('dist')[:10]
    result = []
    for fd in fooddist:
        temp = {}
        temp['food_no'] = fd.foody.food_no
        temp['food_name'] = fd.foody.food_name
        temp['cal'] = fd.foody.cal
        temp['manufacturer'] = fd.foody.manufacturer
        temp['amount'] = fd.foody.amount
        
        print(fd.foody)
        food_nutrient = FoodnutrientSerializer(fd.foody.foodnutrient_set.all(),many=True)
        
        nutri_list = []

        for fn in food_nutrient.data:
            nutri_list.append({"nutrient" : fn['nutrient'], "content" : fn['content']})

        temp['nutrient'] = nutri_list
        result.append(temp)

    return Response(result)


def getNearestUser(login_user):
    global recommend_food
    
    user_flavor_dict = {
        'pref' : [],
        'user' : []
    }

    cols = ['spicy','sweet','sour','salty','oily','savory','bitter','mild','soft','chewy','dry','crispy']
    users = User.objects.all()

    for u in users:
        user_flavor_dict['user'].append(u.user_no)
        pref = []
        for col in cols:
            pref.append(u.__dict__[col])
        user_flavor_dict['pref'].append(pref)
    
    
    def cos_sim(A, B):
       return dot(A, B)/(norm(A)*norm(B))

    print(user_flavor_dict)
    cos = []

    login_user_index = None
    for i in range(len(user_flavor_dict['user'])):
        if user_flavor_dict['user'][i] == login_user:
            login_user_index = i
            break

    for i in range(len(user_flavor_dict['pref'])):
        cos.append(cos_sim(user_flavor_dict['pref'][login_user_index],user_flavor_dict['pref'][i]))

    print(cos)
    topid = sorted(range(len(cos)),key= lambda i: cos[i])[-10:]
    topid.reverse()

    print(topid)
    nearest_users = []
    for i in topid:
        if user_flavor_dict['user'][i] != login_user:
            nearest_users.append(user_flavor_dict['user'][i])
    
    print(nearest_users)

    recommend = Review.objects.filter(user__in = nearest_users, score__gt = 3).order_by('-score')[:20]
    print(recommend)
    
    foodlist = []
    for rec in recommend:
        foodlist.append(rec.food)
        
    recommend_food[login_user] = foodlist

def insertFoodDist():
    foods = Food.objects.filter(food_type="general")

    cols = ['spicy','sweet','sour','salty','oily','savory','bitter','mild','soft','chewy','dry','crispy']
    for i in foods:
        for j in foods:
            dist = 0
            for col in cols:
                dist += (i.__dict__[col] - j.__dict__[col]) ** 2
            
            dist = math.sqrt(dist)
            Fooddist.objects.create(foodx=i, foody=j, dist=dist)

def contentFiltering(user_no):
    global recommend_food

    user = User.objects.get(user_no=user_no)
    foods = Food.objects.filter(food_type="general")

    cols = ['spicy','sweet','sour','salty','oily','savory','bitter','mild','soft','chewy','dry','crispy']
    
    nearest_food = []

    for i in foods:
        dist = 0
        for col in cols:
            dist += (i.__dict__[col] - user.__dict__[col]) ** 2           
        dist = math.sqrt(dist)
        nearest_food.append({'dist' : dist, 'food' : i})

    print(nearest_food)
    
    result = sorted(nearest_food, key= lambda i: i['dist'])[:20]

    reco_list = []
    for item in result:
        reco_list.append(item['food'])
    
    recommend_food[user_no] = reco_list
    
    

def setRecommendFood(user_no):
    loginUser = User.objects.get(user_no=user_no)

    if loginUser.review_cnt > 10:
        getNearestUser(user_no)
    else:
        contentFiltering(user_no)
    