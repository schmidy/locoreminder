// routes.js

app.config(function ($stateProvider) {

    $stateProvider
        .state('login', {
            url: '/login',
            // ...
            data: {
                requireLogin: false
            }
        })
        .state('app', {
            abstract: true,
            // ...
            data: {
                requireLogin: true // this property will apply to all children of 'app'
            }
        })
        .state('app.dashboard', {
            // child state of `app`
            // requireLogin === true
        })

});