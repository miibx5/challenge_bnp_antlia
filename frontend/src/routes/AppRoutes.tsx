import { Home } from "@mui/icons-material";
import { useEffect } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import AppHome from "../pages/home/AppHome";
import ListProductCosif from "../pages/products/cosif/ListProductCosif";
import ListProducts from "../pages/products/ListProducts";
import { useSidebarContext } from "../shared/contexts/sidebar/SidebarContext";



const AppRoutes = () => {
    const { setSidebarOptions } = useSidebarContext();
    useEffect(() => {
        const options = [
            { icon: <Home />, label: 'Home', path: '/home' },
            { icon: <Home />, label: 'Procucts', path: '/products' },
            { icon: <Home />, label: 'Procucts Cosif', path: '/products/cosif' }];
        setSidebarOptions(options);
    }, []);
    return (
        <Routes>
            <Route path="/home" element={<AppHome />} />
            <Route path="/products" element={<ListProducts />} />
            <Route path="/products/cosif" element={<ListProductCosif />} />
            <Route path="*" element={<Navigate to="/home" />} />
        </Routes>

    );
};

export default AppRoutes;