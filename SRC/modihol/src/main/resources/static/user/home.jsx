// const { useState, useEffect } = React;

// const Home = () => {

//   const [destinations, setDestinations] = useState([]);

//   // Gọi API Spring Boot
//   useEffect(() => {
//     fetch("http://localhost:8080/api/destinations")
//       .then((res) => res.json())
//       .then((data) => {
//         setDestinations(data);
//       })
//       .catch((error) => {
//         console.error("Lỗi gọi API:", error);
//       });
//   }, []);

//   return (
//     <div className="min-h-screen bg-slate-50 font-sans text-slate-900">

//       {/* Hero Section */}
//       <section className="relative h-[80vh] flex items-center justify-center overflow-hidden">
//         <div className="absolute inset-0 z-0">
//           <img
//             src="https://images.unsplash.com/photo-1571896349842-33c89424de2d?auto=format&fit=crop&q=80&w=2000"
//             alt="Luxury Resort"
//             className="w-full h-full object-cover brightness-75"
//           />
//         </div>

//         <div className="relative z-10 text-center px-4 max-w-4xl mx-auto">
//           <h1 className="text-5xl md:text-7xl font-bold text-white mb-6">
//             Discover Your Next
//             <span className="text-emerald-400"> Paradise</span>
//           </h1>

//           <p className="text-xl text-slate-100 mb-10">
//             Experience world-class luxury and unparalleled comfort.
//           </p>
//         </div>
//       </section>

//       {/* Featured Destinations */}
//       <section className="py-24 px-4 max-w-7xl mx-auto">

//         <div className="flex justify-between items-end mb-12">
//           <div>
//             <h2 className="text-3xl font-bold text-slate-800 mb-2">
//               Featured Destinations
//             </h2>

//             <p className="text-slate-500">
//               Explore our most popular choices this month
//             </p>
//           </div>

//           <button className="flex items-center gap-1 text-emerald-600 font-semibold">
//             View all
//             {/* Thay thế icon ChevronRight bằng SVG */}
//             <svg className="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
//               <polyline points="9 18 15 12 9 6" />
//             </svg>
//           </button>
//         </div>

//         {/* Render dữ liệu từ API */}
//         <div className="grid grid-cols-1 md:grid-cols-3 gap-8">

//           {destinations.map((item) => (

//             <div
//               key={item.id}
//               className="group relative rounded-3xl overflow-hidden shadow-xl cursor-pointer"
//             >

//               <img
//                 src={item.image}
//                 alt={item.name}
//                 className="w-full h-[400px] object-cover transition-transform duration-700 group-hover:scale-110"
//               />

//               <div className="absolute inset-0 bg-gradient-to-t from-black/80 via-black/20 to-transparent flex flex-col justify-end p-8 text-white">

//                 <div className="flex items-center gap-1 mb-2">
//                   {[...Array(5)].map((_, i) => (
//                     // Thay thế icon Star bằng SVG
//                     <svg key={i} className="w-4 h-4 fill-yellow-400 text-yellow-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
//                       <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" />
//                     </svg>
//                   ))}
//                 </div>

//                 <h3 className="text-2xl font-bold mb-1">
//                   {item.name}
//                 </h3>

//                 <p className="text-slate-300">
//                   Starting from {item.price}
//                 </p>

//               </div>
//             </div>

//           ))}

//         </div>
//       </section>
//     </div>
//   );
// };

// // Render component
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<Home />);


const {useState, useEffect} = React;
const Header = ({activePage, userNaem}) => {
  return (
    <>
      {/* menu nav */}
      <nav>
        <a >
          <p>{activePage === 'home' ? 'trang chủ' : ''}</p>
        </a>
      </nav>
    </>
  );
};
function Home(){
  return(
    <>
    <h1>Home</h1>
    <Header activePage= "home"/>
    </>
  );
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Home />);

