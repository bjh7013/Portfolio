from rest_framework import serializers
from user.models import User, Diethistory, Complist, Flavor, Flavorpref, Food, Foodflavor, Foodnutrient, Foodpref, Preftest, Preftestdetail, Review, Fooddist

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'

class FoodSerializer(serializers.ModelSerializer):
    class Meta:
        model = Food
        fields = '__all__'

class DiethistorySerializer(serializers.ModelSerializer):
    food = FoodSerializer(read_only=True)
    user = UserSerializer(read_only=True)
    food_no = serializers.IntegerField(write_only=True,required=True)
    user_no = serializers.IntegerField(write_only=True,required=True)

    def create(self, validated_data):
        food_data = Food.objects.get(pk=validated_data.pop('food_no'))
        user_data = User.objects.get(pk=validated_data.pop('user_no'))
        return  Diethistory.objects.create(user=user_data,food=food_data,eat_amount=validated_data.pop('eat_amount'),eat_type=validated_data.pop('eat_type'), eat_date=validated_data.pop('eat_date'))
    
    def update(self, instance, validated_data):
        print("dddddddddddddddddddddddd")
        if validated_data.get('food_no'):
            instance.food = Food.objects.get(pk=validated_data.get('food_no'))
        instance.eat_type = validated_data.get('eat_type', instance.eat_type)
        instance.eat_amount = validated_data.get('eat_amount', instance.eat_amount)
        instance.eat_date = validated_data.get('eat_date', instance.eat_date)
    
        instance.save()
        return instance

    class Meta:
        model = Diethistory
        fields = '__all__'

class ComplistSerializer(serializers.ModelSerializer):
    food = FoodSerializer(read_only=True)
    user = UserSerializer(read_only=True)
    food_no = serializers.IntegerField(write_only=True,required=True)
    user_no = serializers.IntegerField(write_only=True,required=True)

    def create(self, validated_data):
        food_data = Food.objects.get(pk=validated_data.pop('food_no'))
        user_data = User.objects.get(pk=validated_data.pop('user_no'))

        return  Complist.objects.create(user=user_data,food=food_data)

    class Meta:
        model = Complist
        fields = '__all__'

class FlavorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Flavor
        fields = '__all__'
        
class FlavorprefSerializer(serializers.ModelSerializer):
    class Meta:
        model = Flavorpref
        fields = '__all__'

class FoodflavorSerializer(serializers.ModelSerializer):
    food = FoodSerializer(read_only=True)
    flavor = FlavorSerializer(read_only=True)
    class Meta:
        model = Foodflavor
        fields = '__all__'

class FoodprefSerializer(serializers.ModelSerializer):
    class Meta:
        model = Foodpref
        fields = '__all__'

class FoodnutrientSerializer(serializers.ModelSerializer):
    food = FoodSerializer(read_only=True)
    class Meta:
        model = Foodnutrient
        fields = '__all__'


class PreftestdetailSerializer(serializers.ModelSerializer):
    food_no = serializers.IntegerField(write_only=True)

    class Meta:
        model = Preftestdetail
        fields = '__all__'

class PreftestSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    user_no = serializers.IntegerField(write_only=True,required=True)
    test_details = serializers.ListField()
    
    # def create(self, validated_data):
    #     tracks_data = validated_data.pop('tracks')
    #     album = Album.objects.create(**validated_data)
    #     for track_data in tracks_data:
    #         Track.objects.create(album=album, **track_data)
    #     return album
    
    def create(self, validated_data):
        user_data = User.objects.get(pk=validated_data.pop('user_no'))
        # print(self.test_details)
        test_details = validated_data.pop('test_details')

        try:
            test = Preftest.objects.create(user=user_data)

            for test_detail_data in test_details:
                food = Food.objects.get(pk=test_detail_data['food_no'])
                Preftestdetail.objects.create(test=test, food=food, score=test_detail_data['score'])
        except:
            test.delete()
        return test

    class Meta:
        model = Preftest
        fields = ('test_no','user','test_date','user_no','test_details')

class ReviewSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    food = FoodSerializer(read_only=True)
    user_no = serializers.IntegerField(write_only=True,required=True) 
    food_no = serializers.IntegerField(write_only=True,required=True)
    
    def create(self, validated_data):
        print("dfsdfsdfsdfsdfs")
        user_data = User.objects.get(pk=validated_data.pop('user_no'))
        food_data = Food.objects.get(pk=validated_data.pop('food_no'))
        return Review.objects.create(user=user_data,food=food_data,score=validated_data.pop('score'),content=validated_data.pop('content', None))
    
    def update(self, instance, validated_data):
        print("dddddddddddddddddddddddd")
        instance.score = validated_data.get('score', instance.score)
        instance.content = validated_data.get('content', instance.content)
    
        instance.save()
        return instance
    
    class Meta:
        model = Review
        fields = '__all__'

class FooddetailSerializer(serializers.Serializer):
    food = FoodSerializer(read_only=True)
    foodnutrient = FoodnutrientSerializer(read_only=True)
    foodflavor = FoodflavorSerializer(read_only=True)
    class Meta:
        fields = '__all__'

class FooddistSerializer(serializers.Serializer):
    # foodx = FoodSerializer(read_only=True)
    foody = FoodSerializer(read_only=True)

    class Meta:
        model = Fooddist
        fields = '__all__'