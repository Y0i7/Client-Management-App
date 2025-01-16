import React from "react";

export const HeaderComponent = () => {
    return (
        <header id="header">
            <nav className="navbar navbar-expand-md navbar-dark">
                <div>
                    <a href="/" className="navbar-brand">Clients Management</a>
                </div>
            </nav>
        </header>
    );
};

export default HeaderComponent;