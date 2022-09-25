import { ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import React, { ReactNode } from 'react';
import { useMatch, useNavigate, useResolvedPath } from 'react-router-dom';

type SidebarItemProps = {
    icon: ReactNode,
    label: string,
    to: string,
    onClick?: (() => void) | undefined;
};

const SidebarItemLink: React.FC<SidebarItemProps> = ({ icon, label, to, onClick }) => {
    const navigate = useNavigate();
    const resolvedPath = useResolvedPath(to);
    const match = useMatch({ path: resolvedPath.pathname, end: false });
    const handleClick = () => {
        navigate(to);
        onClick?.();
    };
    return (
        <ListItemButton onClick={handleClick} selected={!!match}>
            <ListItemIcon>
                {icon}
            </ListItemIcon>
            <ListItemText primary={label} />
        </ListItemButton>

    );
};

export default SidebarItemLink;