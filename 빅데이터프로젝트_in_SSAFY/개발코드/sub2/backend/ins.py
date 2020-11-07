# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Diethistory(models.Model):
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no')
    eat_date = models.DateTimeField()
    eat_type = models.CharField(max_length=1)
    nutrient = models.CharField(max_length=20)
    eat_amount = models.IntegerField()

    class Meta:
        db_table = 'diethistory'
        unique_together = (('user_no', 'eat_date'),)

class Complist(models.Model):
    food_no = models.ForeignKey('Food', models.DO_NOTHING, db_column='food_no')
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no')

    class Meta:
        db_table = 'complist'
        unique_together = (('user_no', 'food_no'),)


class Flavor(models.Model):
    flavor_no = models.IntegerField(primary_key=True)
    flavor_name = models.CharField(max_length=30, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'flavor'


class Flavorpref(models.Model):
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no', primary_key=True)
    flavor_no = models.ForeignKey(Flavor, models.DO_NOTHING, db_column='flavor_no')
    pref_score = models.FloatField()

    class Meta:
        managed = False
        db_table = 'flavorpref'
        unique_together = (('user_no', 'flavor_no'),)


class Food(models.Model):
    food_no = models.AutoField(primary_key=True)
    food_name = models.CharField(max_length=50)
    food_type = models.CharField(max_length=10)
    price = models.IntegerField(blank=True, null=True)
    cal = models.IntegerField(blank=True, null=True)
    manufacturer = models.CharField(max_length=50, blank=True, null=True)
    group_no = models.ForeignKey('self', models.DO_NOTHING, db_column='group_no', blank=True, null=True)
    store_no = models.ForeignKey('Store', models.DO_NOTHING, db_column='store_no', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'food'


class Foodflavor(models.Model):
    food_no = models.ForeignKey(Food, models.DO_NOTHING, db_column='food_no', primary_key=True)
    flavor_no = models.ForeignKey(Flavor, models.DO_NOTHING, db_column='flavor_no')

    class Meta:
        managed = False
        db_table = 'foodflavor'
        unique_together = (('food_no', 'flavor_no'),)


class Foodnutrient(models.Model):
    nutrient = models.CharField(max_length=20)
    content = models.IntegerField()
    food_no = models.ForeignKey(Food, models.DO_NOTHING, db_column='food_no')

    class Meta:
        managed = False
        db_table = 'foodnutrient'
        unique_together = (('food_no', 'nutrient'),)


class Foodpref(models.Model):
    pref_score = models.FloatField()
    food_no = models.ForeignKey(Food, models.DO_NOTHING, db_column='food_no')
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no')

    class Meta:
        managed = False
        db_table = 'foodpref'
        unique_together = (('user_no', 'food_no'),)


class Preftest(models.Model):
    test_no = models.AutoField(primary_key=True)
    test_date = models.DateTimeField()
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no')

    class Meta:
        managed = False
        db_table = 'preftest'


class Preftestdetail(models.Model):
    score = models.FloatField()
    food_no = models.ForeignKey(Food, models.DO_NOTHING, db_column='food_no')
    test_no = models.ForeignKey(Preftest, models.DO_NOTHING, db_column='test_no')

    class Meta:
        managed = False
        db_table = 'preftestdetail'
        unique_together = (('test_no', 'food_no'),)


class Review(models.Model):
    review_no = models.AutoField(primary_key=True)
    write_date = models.DateTimeField()
    score = models.FloatField()
    content = models.CharField(max_length=200, blank=True, null=True)
    food_no = models.ForeignKey(Food, models.DO_NOTHING, db_column='food_no')
    user_no = models.ForeignKey('User', models.DO_NOTHING, db_column='user_no')

    class Meta:
        managed = False
        db_table = 'review'


class Store(models.Model):
    store_no = models.AutoField(primary_key=True)
    store_name = models.CharField(max_length=30)
    branch_name = models.CharField(max_length=30, blank=True, null=True)
    area = models.CharField(max_length=20, blank=True, null=True)
    tel_no = models.CharField(max_length=20, blank=True, null=True)
    address = models.CharField(max_length=150)
    lat = models.FloatField()
    lng = models.FloatField()

    class Meta:
        managed = False
        db_table = 'store'


class Storecategory(models.Model):
    category = models.CharField(max_length=30)
    store_no = models.ForeignKey(Store, models.DO_NOTHING, db_column='store_no')

    class Meta:
        managed = False
        db_table = 'storecategory'
        unique_together = (('store_no', 'category'),)


class User(models.Model):
    user_no = models.AutoField(primary_key=True)
    user_id = models.CharField(unique=True, max_length=20)
    user_pw = models.CharField(max_length=20, blank=True, null=True)
    user_name = models.CharField(max_length=20, blank=True, null=True)
    nick_name = models.CharField(max_length=20)
    gender = models.CharField(max_length=1, blank=True, null=True)
    address = models.CharField(max_length=130)
    height = models.IntegerField(blank=True, null=True)
    weight = models.IntegerField(blank=True, null=True)
    born_year = models.IntegerField(blank=True, null=True)
    plan_carb = models.IntegerField(blank=True, null=True)
    plan_protein = models.IntegerField(blank=True, null=True)
    plan_fat = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'


