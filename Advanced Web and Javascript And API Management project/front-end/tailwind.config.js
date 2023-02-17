/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [ "./src/**/*.{js,jsx,ts,tsx}",],
  theme: {
    extend: {},
  },
 plugins: [require('tailwind-scrollbar-hide')],

//  plugins: [
//   function ({ addUtilities }) {
//     addUtilities({
//       '.overflow-initial': { overflow: 'initial' },
//       '.overflow-inherit': { overflow: 'inherit' },
//     })
//   }
// ]
}
