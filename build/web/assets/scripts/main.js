/**
 * Created by Mike on 3/14/2015.
 */


angular.module('geoReminder', ['ngResource'])
    .controller("AccountController", function($scope, $http) {
        $scope.TestVar = "Mike";
        $scope.loginForm = {};

        $scope.loginForm.submitTheForm = function(item, event) {
            console.log("--> Submitting login form for account "+$scope.loginForm.userName);

            var responsePromise = $http({
                url: "/GeoReminder/webresources/auth/",
                method: "GET",
                withCredentials : true,
                params: {userName : $scope.loginForm.userName,
                    password : $scope.loginForm.password}
            }).success(function(data){
                $rootScope.sessionValues = eval(data);
                console.log($rootScope.sessionValues); /*Returns desired string*/
            }).error(function() {
                $scope.message="Some error has occured";
            });
        }
    });

angular.module('App.controllers').controller('ResourceController',function($scope, Entry) {
    var entry = Entry.get({ id: $scope.id }, function() {
        console.log(entry);
    }); // get() returns a single entry

    var entries = Entry.query(function() {
        console.log(entries);
    }); //query() returns all the entries

    $scope.entry = new Entry(); //You can instantiate resource class

    $scope.entry.data = 'some data';

    Entry.save($scope.entry, function() {
        //data saved. do something here.
    }); //saves an entry. Assuming $scope.entry is the Entry object
});


//app.factory("Post", function($resource) {
//    return $resource("/api/posts/:id");
//});

//function accountCtrl($scope) {
//
//
//
//}