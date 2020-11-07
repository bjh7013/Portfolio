from django.urls import path
from . import views

urlpatterns = [
    path("food",views.food_list, name="food"),
    path("foodC",views.create, name="foodCreate"),
    path("complist", views.getComplist, name="complist"),
    path("compitem", views.compitemC, name="compitem"),
    path("compitem/<int:id>", views.compitemD),

    path("diethistory/<int:id>/<int:startdate>/<int:enddate>", views.diethistory),
    path("diethistory/<int:id>", views.diethistory),
    path("diethistory/<int:startdate>/<int:enddate>", views.diethistory2),
    path("diethistory", views.diethistory),

    path("preftest", views.preftest),
    path("review",views.review),
    path("review/<int:id>",views.review),

    # 이름으로(포함) 음식리스트(일반음식만 - normal) 조회
    path("namefood/<str:food_name>",views.namefood_list),

    # 이름으로 음식리스트(인스턴트, 외식품목) 조회
    path("searchsubfood/<str:food_name>", views.searchsubfood),
    # 음식 상세조회(음식정보 + 영양성분리스트 + 음식점리스트 + 맛)
    path("fooddetail/<int:food_no>",views.food_detail),
    # 음식 상세조회 시 대표품목에 해당하는 인스턴트 및 외식식품 조회하기
    path("subfoodlist/<int:food_no>", views.get_subfood_list),
    path("relatedfood/<int:food_no>", views.get_realted_food),
    # 음식으로 영양성분리스트 조회
    path("foodnutrient/<int:food_no>", views.foodnutrient_list),
    # 영양분으로 음식리스트 조회
    path("nutrientfood/<str:nut1>/<int:val1>/<str:nut2>/<int:val2>", views.nutrientfood_list), 

    # 음식으로 식당리스트 조회(음식번호)
    # path("store/<int:food_no>", views.store_list),
    # 식당 상세조회(식당번호)
    # path("storedetail/<int:store_no>", views.store_detail),
    # 카테고리로 식당리스트 조회
    # path("categorystore/<str:category>", views.categorystore_list),
    # 맛으로 음식리스트 조회
    path("flavorfood/<str:flavor_name>", views.flavorfood_list),
    # 음식으로 맛리스트 조회
    path("foodflavor/<int:food_no>", views.foodflavor_list),

    # 음식 맛 점수 수정
    path("foodupdate/<int:id>", views.food_update),

    # 회원가입
    path('join', views.join, name="join"),
    path('join/idcheck', views.id_check, name="idcheck"),
    # 로그인
    path('login',views.login, name="login"),
    # 마이페이지
    path('mypage', views.mypage, name="mypage"),
    path('recommend', views.getReco)
]