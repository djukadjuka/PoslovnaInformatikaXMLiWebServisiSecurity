app.controller("business_partner_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','business_partner');
     $cookies.put('subObjectsOne','company');
     $cookies.put('subObjectsTwo',null);
     $cookies.put('subObjectsThree',null);
     $cookies.put('subObjectsFour',null);

     if($cookies.get('state')==null || $cookies.get('state')==""){
        $cookies.put('state','edit');
     }

     $scope.setObjects();
     $scope.setSubObjects();

     $scope.obj={};
     $scope.state=$cookies.get('state');

     $scope.foo = function(event, obj) {
        $(".highlighted").removeClass("highlighted");
        $(event.currentTarget).addClass("highlighted");
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.business_partner_id=$(event.currentTarget).find(".id").html();
            $scope.obj.name=$(event.currentTarget).find(".name").html();
            $scope.obj.tin=parseInt($(event.currentTarget).find(".tin").html());
            $scope.obj.city=$(event.currentTarget).find(".city").html();
            $scope.obj.adress=$(event.currentTarget).find(".adress").html();
            $scope.obj.telephone=$(event.currentTarget).find(".telephone").html();
            $scope.obj.personal_number=parseInt($(event.currentTarget).find(".personal_number").html());
            $scope.obj.current_account=$(event.currentTarget).find(".current_account").html();
            $scope.obj.type_of_bp=$(event.currentTarget).find(".type_of_bp").html();


            i=$(event.currentTarget).find(".company_id").html();
            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;
        }
     }

     $scope.sync = function(item){
        $scope.$apply(function() {
            if($scope.state=="edit"){
                $scope.obj={};
                $scope.obj.business_partner_id_id=item.find(".id").html();
                $scope.obj.name=item.find(".name").html();
                $scope.obj.tin=parseInt(item.find(".tin").html());
                $scope.obj.city=item.find(".city").html();
                $scope.obj.adress=item.find(".adress").html();
                $scope.obj.telephone=item.find(".telephone").html();
                $scope.obj.personal_number=parseInt(item.find(".personal_number").html());
                $scope.obj.current_account=item.find(".current_account").html();
                $scope.obj.type_of_bp=item.find(".type_of_bp").html();

                i=item.find(".company_id").html();
                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
                $scope.obj.company=result;
            }
        });
     }

    $("#nextform").click(function(){
        alert("kliknuto na next form");
        /*highlighted = $(".highlighted");
        id = highlighted.find(".id").html();
        //nextForm(id);
        myService.set(id);
        $window.location.href = '/#/settlement';*/
    });


    $scope.fook = function(event, obj) {
         $("#highlightedOne").removeClass("highlighted");
         $('#highlightedOne').removeAttr('id');
         $(event.currentTarget).attr('id', 'highlightedOne');
         $(event.currentTarget).addClass("highlighted");
      };

      $("#moPickup").off().on('click', function() {
        $scope.$apply(function() {
            i=$("#highlightedOne").find(".sooid").html();
            $('#modalOne').modal('toggle');

            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;
        });
    });



});