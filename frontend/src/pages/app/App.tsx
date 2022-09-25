import { useState } from 'react';
import { HashRouter } from 'react-router-dom';
import AppRoutes from '../../routes/AppRoutes';
import Sidebar from '../../shared/components/sidebar/Sidebar';
import { SidebarContextProvider } from '../../shared/contexts/sidebar/SidebarContext';
function App() {
    const [count, setCount] = useState(0);

    return (
        <SidebarContextProvider>
            <HashRouter>
                <Sidebar>
                    <AppRoutes />
                </Sidebar>
            </HashRouter>
        </SidebarContextProvider>
    );
}

export default App;
