import { createContext, ReactNode, useCallback, useContext, useState } from "react";

type SidebarOptionsProps = {
    icon: ReactNode,
    label: string,
    path: string;
};

type SidebarContextProps = {
    isSidebarOpen: boolean,
    setIsSidebarOpen: () => void,
    sidebarOptions: SidebarOptionsProps[],
    setSidebarOptions: (newSidebarOptions: SidebarOptionsProps[]) => void;
};

type SideBarContextProviderProps = { children: ReactNode; };

const SidebarContext = createContext({} as SidebarContextProps);

export const useSidebarContext = () => useContext(SidebarContext);


export const SidebarContextProvider: React.FC<SideBarContextProviderProps> = ({ children }) => {
    const [isSidebarOpen, setIsSidebarOpen] = useState<boolean>(false);
    const [sidebarOptions, setSidebarOptions] = useState<SidebarOptionsProps[]>([]);
    const handeSetIsSidebarOpen = useCallback(() => { setIsSidebarOpen(oldSidebarIsOpen => !oldSidebarIsOpen); }, []);
    const handeSetSidebarOptions = useCallback((newSidebarOptions: SidebarOptionsProps[]) => { setSidebarOptions(newSidebarOptions); }, []);
    return (
        <SidebarContext.Provider value={{ isSidebarOpen, sidebarOptions, setIsSidebarOpen: handeSetIsSidebarOpen, setSidebarOptions: handeSetSidebarOptions }}>
            {children}
        </SidebarContext.Provider>
    );
}

