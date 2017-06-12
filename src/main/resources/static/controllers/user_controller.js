app.controller("user_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','user');
    $cookies.put('subObjectsOne','company');
    $cookies.put('subObjectsTwo','business_partner');
    $cookies.put('subObjectsThree','role');
    $cookies.put('subObjectsFour',null);

    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

      $scope.setObjects();
      $scope.setSubObjects();
      $scope.setSubObjectsTwo();
      $scope.setSubObjectsThree();


      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.user_id=$(event.currentTarget).find(".id").html();
             $scope.obj.username=$(event.currentTarget).find(".username").html();
             $scope.obj.password=$(event.currentTarget).find(".password").html();

             i=$(event.currentTarget).find(".company_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
             $scope.obj.company=result;


            var res=$scope.objectsTwo.filter(function (el) {
                  return el.company.company_id==i && el.type_of_bp!="supplier";
                });
             $scope.subObjectsTwo=res;
             ii=$(event.currentTarget).find(".business_partner_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +ii; })[ 0 ];
             $scope.obj.business_partner=resultt;

             iii=$(event.currentTarget).find(".role_id").html();
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +iii; })[ 0 ];
             $scope.obj.role=resulttt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.purchase_order_id=item.find(".id").html();
                  $scope.obj.username=item.find(".username").html();
                  $scope.obj.password=item.find(".password").html();

                   i=$item.find(".company_id").html();
                   var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
                   $scope.obj.company=result;


                  /*var res=$scope.objectsTwo.filter(function (el) {
                        return el.company.company_id==i && el.type_of_bp!="supplier";
                      });
                   $scope.subObjectsTwo=res;*/
                   ii=item.find(".business_partner_id").html();
                   var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +ii; })[ 0 ];
                   $scope.obj.business_partner=resultt;

                   iii=item.find(".role_id").html();
                   var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +iii; })[ 0 ];
                   $scope.obj.role=resulttt;
              }
          });
       };

       $scope.$watch('obj.company', function (newValue, oldValue) {
           if(newValue!=oldValue){
               $scope.findBPandFY();
           }
       });

       $scope.findBPandFY=function(){
           if($scope.obj.company!=null){
                id=$scope.obj.company.company_id;
                var result=$scope.objectsTwo.filter(function (el) {
                  return el.company.company_id==id && el.type_of_bp!="supplier";
                });
                $scope.subObjectsTwo=result;
           }
           else{
                $scope.subObjectsTwo={};
           }
       }

       $scope.$watch('obj.business_partner', function (newValue, oldValue) {
              if(newValue!=oldValue){
                  if($scope.obj.business_partner!=null){
                       var result=$scope.objectsThree.filter(function( obj ) { return obj.name == "korisnik"; });
                       $scope.subObjectsThree=result;
                       $scope.obj.role=result[0];
                  }else{
                        var result=$scope.objectsThree.filter(function( obj ) { return obj.name != "korisnik"; });
                       $scope.subObjectsThree=result;
                  }
              }
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

        $scope.fookk = function(event, obj) {
             $("#highlightedTwo").removeClass("highlighted");
             $('#highlightedTwo').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedTwo');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#mtPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedTwo").find(".sotid").html();
                $('#modalTwo').modal('toggle');

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +i; })[ 0 ];
                $scope.obj.business_partner=result;
            });
        });

        $scope.fookkk = function(event, obj) {
             $("#highlightedThree").removeClass("highlighted");
             $('#highlightedThree').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedThree');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#mthPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedThree").find(".sothid").html();
                $('#modalThree').modal('toggle');

                var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +i; })[ 0 ];
                $scope.obj.role=result;
            });
        });
});