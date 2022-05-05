module.exports = {
    content: [
        './resources/public/index.html',
        process.env.NODE_ENV == 'production'
            ? "./resources/public/js/compiled/main.js"
            : "./resources/public/js/compiled/**/*.js"
    ],
    theme: {
        extend: {},
    },
    plugins: [
        require('@tailwindcss/forms'),
        require('tailwindcss-font-inter')
    ]
}
