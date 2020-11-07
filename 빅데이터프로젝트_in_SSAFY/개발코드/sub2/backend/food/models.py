from django.db import models
from django.contrib.auth.models import AbstractUser


    
# class Diethistory(models.Model):
#     user_no = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)                      # 회원 번호
#     eat_date = models.DateTimeField(null=False)                                                                         # 섭취 일자 
#     eat_type = models.CharField(max_length=1, null=False)                                                               # 섭취 구분(b-아침|l-점심|d-저녁|s-간식)
#     nutrient = models.CharField(max_length=20, null=False)                                                          # 영양소
#     eat_amount = models.IntegerField(null=False)                                                                    # 섭취량
#     class Meta:
#         db_table = 'diethistory'
#         unique_together = (('user_no', 'eat_date'),)

# class CustomUser(AbstractUser):
#     def __str__(self):
#         return self.username

# class Flavorpref(models.Model):
#     user_no = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)                      # 회원 번호
#     flavor = models.CharField(max_length=20, null=False)                                                                # 맛
#     pref_score = models.FloatField(null=False)                                                                          # 선호도
#     class Meta:
#         db_table = 'flavorpref'
#         unique_together = (('user_no', 'flavor'),)

# class Foodpref(models.Model):
#     user_no = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)                       # 회원번호
#     food_no = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)                       # 음식번호
#     pref_score = models.FloatField(null=False)                                                      # 선호도
#     class Meta:
#         db_table = 'foodpref'
#         unique_together = (('user_no', 'food_no'),)

# class Preftest(models.Model):
#     test_no = models.AutoField(primary_key=True, null=False)                                                          # 검사번호
#     user_no = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)                       # 회원번호
#     test_date = models.DateTimeField(null=False, auto_now_add=True)                                 # 검사일시
#     class Meta:
#         db_table = 'preftest'
#         # unique_together = (('test_no', 'user_no'),)

# class User(models.Model):
#     user_no = models.AutoField(primary_key=True, null=False)                                        # 회원번호
#     user_id = models.CharField(unique=True, max_length=20, null=False)                              # 아이디
#     user_pw = models.CharField(max_length=20, null=True)                                           # 비밀번호
#     user_name = models.CharField(max_length=20, null=True)                                         # 이름
#     nick_name = models.CharField(max_length=20, blank=False, null=False)                                         # 닉네임
#     gender = models.CharField(max_length=1, blank=False, null=False, default='m')                                # 성별 m-남|f-여 
#     address = models.CharField(max_length=130, blank=False, null=False)                                          # 주소
#     height = models.IntegerField(null=True, blank=True)                                             # 키
#     weight = models.IntegerField(null=True, blank=True)                                             # 몸무게
#     born_year = models.IntegerField(null=True, blank=True)                                          # 출생년도
#     plan_carb = models.IntegerField(null=True, blank=True)
#     plan_protein = models.IntegerField(null=True, blank=True)
#     plan_fat = models.IntegerField(null=True, blank=True)                               
#     class Meta:
#         db_table = 'user'

# # Create your models here.
# class Food(models.Model):
#     food_no = models.AutoField(primary_key=True, null=False)                                                            # 음식 번호
#     food_name = models.CharField(max_length=50, null=False)                                                             # 음식명
#     food_type = models.CharField(max_length=10, null=False)                                                             # 음식 구분(menu-식당메뉴|instant-가공식품|general-일반음식)
#     price = models.IntegerField(null=True, blank=True)                                                                  # 가격(식당 메뉴인 경우)
#     cal = models.IntegerField(null=True, blank=True)                                                                    # 칼로리
#     manufacturer = models.CharField(max_length=50, null=True, blank=True)                                               # 제조사(가공식품인 경우)
#     store_no = models.ForeignKey('Store', db_column='store_no', null=True, blank=True, on_delete=models.CASCADE)        # 식당번호(식당 메뉴인 경우)
#     group_no = models.ForeignKey('self', db_column='group_no', null=True, blank=True, on_delete=models.CASCADE)          # 음식그룹번호(식당메뉴나 가공식품의 경우, 일반음식의 음식번호로 그룹핑됨)
#     class Meta:
#         db_table = 'food'

# class Foodnutrient(models.Model):
#     food_no = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)                      # 음식번호
#     nutrient = models.CharField(max_length=20, null=False)                                                              # 영양소
#     content = models.IntegerField(null=False)                                                                           # 100g 당 함량
#     class Meta:
#         db_table = 'foodnutrient'
#         unique_together = (('food_no', 'nutrient'),)

# class Review(models.Model):
#     review_no = models.AutoField(primary_key=True, null=False)                                      # 리뷰번호
#     user_no = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)  # 회원번호
#     food_no = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)  # 음식번호
#     write_date = models.DateTimeField(null=False, auto_now_add=True)                                # 작성일시(최초작성일시)
#     score = models.FloatField(null=False)                                                           # 평점
#     content = models.CharField(max_length=200, null=True, blank=True)                               # 평가내용
#     class Meta:
#         db_table = 'review'

# class Store(models.Model):
#     store_no = models.AutoField(primary_key=True, null=False)                                       # 식당번호
#     store_name = models.CharField(max_length=30, null=False)                                        # 식당명
#     branch_name = models.CharField(max_length=30, null=True, blank=True)                            # 지점명
#     area = models.CharField(max_length=20, null=True, blank=True)                                   # 지역명
#     tel_no = models.CharField(max_length=20, null=True, blank=True)                                 # 전화번호
#     address = models.CharField(max_length=150, null=False)                                          # 주소
#     lat = models.FloatField(null=False)                                                             # 위도
#     lng = models.FloatField(null=False)                                                             # 경도
#     class Meta:
#         db_table = 'store'

# class Storecategory(models.Model):
#     store_no = models.ForeignKey('Store', db_column='store_no', null=False, on_delete=models.CASCADE)   # 식당번호
#     category = models.CharField(max_length=30, null=False)                                              # 카테고리
#     class Meta:
#         db_table = 'storecategory'
#         unique_together = (('store_no', 'category'),)

# class Preftestdetail(models.Model):
#     test_no = models.ForeignKey('Preftest', db_column='test_no', null=False, on_delete=models.CASCADE)                   # 검사번호
#     food_no = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)                       # 음식번호
#     score = models.FloatField(null=False)                                                                   # 평점
#     class Meta:
#         db_table = 'preftestdetail'
#         unique_together = (('test_no', 'food_no'),)

# class Complist(models.Model):
#     user_no = models.ForeignKey('User', null=False, on_delete=models.CASCADE, db_column='user_no')
#     food_no = models.ForeignKey('Food', null=False, on_delete=models.CASCADE, db_column='food_no')
#     class Meta:
#         db_table = 'complist'
#         unique_together = (('user_no', 'food_no'),)

#class flavor, foodflavor
