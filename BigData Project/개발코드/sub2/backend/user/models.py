from django.db import models
import datetime
class Diethistory(models.Model):
    history_no = models.AutoField(primary_key=True, null=False)
    user = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE) # 회원 번호
    eat_date = models.DateTimeField(default=datetime.datetime.now)                              # 섭취 일자 
    eat_type = models.CharField(max_length=1, null=False)                                       # 섭취 구분(b-아침|l-점심|d-저녁|s-간식)
    food = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE) # 섭취음식
    eat_amount = models.IntegerField(null=False)                                                # 섭취량

    class Meta:
        db_table = 'diethistory'

class Complist(models.Model):
    user = models.ForeignKey('User', null=False, on_delete=models.CASCADE, db_column='user_no') # 회원 번호
    food = models.ForeignKey('Food', null=False, on_delete=models.CASCADE, db_column='food_no') # 음식 번호
    
    class Meta:
        db_table = 'complist'
        unique_together = (('user', 'food'),)

class Flavor(models.Model):
    flavor_no = models.AutoField(primary_key=True)                         # 맛번호
    flavor_name = models.CharField(max_length=30, null=False, blank=False) # 맛이름

    class Meta:
        db_table = 'flavor'

class Flavorpref(models.Model):
    user = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)      # 회원 번호
    flavor = models.ForeignKey('Flavor', db_column='flavor_no',null=False, on_delete=models.CASCADE) # 맛
    pref_score = models.FloatField(null=False)                                                       # 선호도
    
    class Meta:
        db_table = 'flavorpref'
        unique_together = (('user', 'flavor'),)

class Food(models.Model):
    food_no = models.AutoField(primary_key=True, null=False)                                                  # 음식 번호
    food_name = models.CharField(max_length=50, null=False)                                                   # 음식명
    food_type = models.CharField(max_length=10, null=False)                                                   # 음식 구분(menu-식당메뉴|instant-가공식품|general-일반음식)
    cal = models.IntegerField(null=True, blank=True)                                                          # 칼로리
    manufacturer = models.CharField(max_length=50, null=True, blank=True)                                     # 제조사(가공식품인 경우)
    group = models.ForeignKey('self', db_column='group_no', null=True, blank=True, on_delete=models.CASCADE)  # 음식그룹번호(식당메뉴나 가공식품의 경우, 일반음식의 음식번호로 그룹핑됨)
    amount = models.IntegerField(null=True)

    spicy = models.FloatField(null=True)
    sweet = models.FloatField(null=True)
    sour = models.FloatField(null=True)
    salty = models.FloatField(null=True)
    oily = models.FloatField(null=True)
    savory = models.FloatField(null=True)
    bitter = models.FloatField(null=True)
    mild = models.FloatField(null=True)
    soft = models.FloatField(null=True)
    chewy = models.FloatField(null=True)
    dry = models.FloatField(null=True)
    crispy = models.FloatField(null=True)
    rate_count = models.IntegerField(null=True)
    
    class Meta:
        db_table = 'food'

class Foodflavor(models.Model):
    food = models.ForeignKey('Food', on_delete=models.CASCADE, db_column='food_no', null=False)       # 음식번호
    flavor = models.ForeignKey('Flavor', on_delete=models.CASCADE, db_column='flavor_no', null=False) # 맛번호

    class Meta:
        db_table = 'foodflavor'
        unique_together = (('food', 'flavor'),)
        
class Foodnutrient(models.Model):
    food = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE) # 음식번호
    nutrient = models.CharField(max_length=20, null=False)                                      # 영양소
    content = models.IntegerField(null=False)                                                   # 100g 당 함량
    class Meta:
        db_table = 'foodnutrient'
        unique_together = (('food', 'nutrient'),)


class Foodpref(models.Model):
    user = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE) # 회원번호
    food = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE) # 음식번호
    pref_score = models.FloatField(null=False)                                                  # 선호도
    class Meta:
        db_table = 'foodpref'
        unique_together = (('user', 'food'),)

class Preftest(models.Model):
    test_no = models.AutoField(primary_key=True, null=False)                                    # 검사번호
    user = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE) # 회원번호
    test_date = models.DateTimeField(null=False, auto_now_add=True)                             # 검사일시
    
    class Meta:
        db_table = 'preftest'
        # unique_together = (('test_no', 'user_no'),)

class Preftestdetail(models.Model):
    test = models.ForeignKey('Preftest', db_column='test_no', null=False, on_delete=models.CASCADE) # 검사번호
    food = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)     # 음식번호
    score = models.FloatField(null=False)                                                           # 평점
    class Meta:
        db_table = 'preftestdetail'
        unique_together = (('test', 'food'),)

class User(models.Model):
    # 필수
    user_no = models.AutoField(primary_key=True, null=False)                                        # 회원번호
    user_id = models.CharField(unique=True, max_length=20, null=False)                              # 아이디
    user_pw = models.CharField(max_length=20, null=False)                                           # 비밀번호
    user_name = models.CharField(max_length=20, null=False)                                         # 이름
    nick_name = models.CharField(max_length=20, blank=False, null=False)                            # 닉네임
    gender = models.CharField(max_length=1, blank=False, null=False, default='m')                   # 성별 m-남|f-여 
    born_year = models.IntegerField(null=False, blank=False)                                        # 출생년도
    # 선택
    address = models.CharField(max_length=130, blank=False, null=True)                              # 주소
    height = models.IntegerField(null=True, blank=False)                                            # 키
    weight = models.IntegerField(null=True, blank=False)                                            # 몸무게
    weight_type = models.CharField(max_length=1, blank=False, null=False, default='e')              # 체중관리목표 u-증가|e-유지|d-감소
    plan_carb = models.IntegerField(null=True, blank=False)                                         # 일일 권장 탄수화물
    plan_protein = models.IntegerField(null=True, blank=False)                                      # 일일 권장 단백질
    plan_fat = models.IntegerField(null=True, blank=False)                                          # 일일 권장 지방
    plan_cal = models.IntegerField(null=True, blank=False)
    
    spicy = models.FloatField(default=5)
    sweet = models.FloatField(default=5)
    sour = models.FloatField(default=5)
    salty = models.FloatField(default=5)
    oily = models.FloatField(default=5)
    savory = models.FloatField(default=5)
    bitter = models.FloatField(default=5)
    mild = models.FloatField(default=5)
    soft = models.FloatField(default=5)
    chewy = models.FloatField(default=5)
    dry = models.FloatField(default=5)
    crispy = models.FloatField(default=5)
    review_cnt = models.IntegerField(default=1)
    class Meta:
        db_table = 'user'


class Review(models.Model):
    review_no = models.AutoField(primary_key=True, null=False)                                   # 리뷰번호
    user = models.ForeignKey('User', db_column='user_no', null=False, on_delete=models.CASCADE)  # 회원번호
    food = models.ForeignKey('Food', db_column='food_no', null=False, on_delete=models.CASCADE)  # 음식번호
    write_date = models.DateTimeField(null=False, auto_now_add=True)                             # 작성일시(최초작성일시)
    score = models.FloatField(null=False)                                                        # 평점
    content = models.CharField(max_length=200, null=True)                            # 평가내용
    class Meta:
        db_table = 'review'

class Fooddist(models.Model):
    foodx = models.ForeignKey('Food', db_column='foodx', on_delete=models.CASCADE, related_name='foodx')
    foody = models.ForeignKey('Food', db_column='foody', on_delete=models.CASCADE, related_name='foody')
    dist = models.FloatField()

    class Meta:
        db_table = 'fooddist'
        