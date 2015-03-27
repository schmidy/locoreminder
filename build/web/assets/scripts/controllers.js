'use strict';

/* Controllers */

var app = angular.module('locoReminder.controllers', []);

// Clear browser cache (in development mode)
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('DummyController', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
    $scope.bla = 'bla from controller';
    DummyFactory.get({}, function (dummyFactory) {
        $scope.firstname = dummyFactory.firstName;
    })
}]);

app.controller('ReminderListController', ['$scope', 'ReminderFactory', 'ReminderFactory', '$location',
    function ($scope, RemindersFactory, ReminderFactory, $location) {

        // callback for ng-click 'editReminder':
        $scope.editReminder = function (ReminderId) {
            $location.path('/reminder-detail/' + ReminderId);
        };

        // callback for ng-click 'deleteReminder':
        $scope.deleteReminder = function (ReminderId) {
            ReminderFactory.delete({ id: ReminderId });
            $scope.Reminders = RemindersFactory.query();
            $location.path('/reminder-list');
        };

        // callback for ng-click 'createReminder':
        $scope.createNewReminder = function () {
            $location.path('/reminder-creation');
        };

        $scope.Reminders = RemindersFactory.query();

    }]);

app.controller('ReminderDetailController', ['$scope', '$routeParams', 'ReminderFactory', '$location',
    function ($scope, $routeParams, ReminderFactory, $location) {

        // callback for ng-click 'updateReminder':
        $scope.updateReminder = function () {
            ReminderFactory.update($scope.Reminder);
            $location.path('/reminder-list');
        };

        // callback for ng-click 'cancel':
        $scope.reminderCancel = function () {
            $location.path('/reminder-list');
        };

        $scope.Reminder = ReminderFactory.show({id: $routeParams.id});
    }]);

app.controller('ReminderCreationController', ['$scope', 'AccountsFactory', 'RemindersFactory', '$location',
    function ($scope, AccountsFactory, RemindersFactory, $location) {

        // callback for ng-click 'createNewReminder':
        $scope.createNewReminder = function () {
            RemindersFactory.create($scope.reminder);
            $location.path('/reminder-list');
        }

        // callback for ng-click 'cancel':
        $scope.reminderCancel = function () {
            $location.path('/reminder-list');
        };

        //provide list of accounts
        $scope.Accounts = AccountsFactory.query();

    }]);

app.controller('AccountListController', ['$scope', 'AccountFactory', 'AccountFactory', '$location',
    function ($scope, AccountsFactory, AccountFactory, $location) {

        // callback for ng-click 'editAccount':
        $scope.editAccount = function (AccountId) {
            $location.path('/account-detail/' + AccountId);
        };

        // callback for ng-click 'deleteAccount':
        $scope.deleteAccount = function (AccountId) {
            AccountFactory.delete({ id: AccountId });
            $scope.Accounts = AccountsFactory.query();
            $location.path('/account-list');
        };

        // callback for ng-click 'createAccount':
        $scope.createNewAccount = function () {
            $location.path('/account-creation');
        };

        $scope.Accounts = AccountsFactory.query();

    }]);

app.controller('AccountDetailController', ['$scope', '$routeParams', 'AccountFactory', '$location',
    function ($scope, $routeParams, AccountFactory, $location) {

        // callback for ng-click 'updateReminder':
        $scope.updateAccount = function () {
            AccountFactory.update($scope.Account);
            $location.path('/account-list');
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/account-list');
        };

        $scope.Account = AccountFactory.show({id: $routeParams.id});
    }]);

app.controller('AccountCreationController', ['$scope', 'AccountsFactory', '$location',
    function ($scope, AccountsFactory, $location) {

        // callback for ng-click 'createNewAccount':
        $scope.createNewAccount = function () {
            AccountsFactory.create($scope.Account);
            $location.path('/account-list');
        }

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/account-list');
        };
    }]);
