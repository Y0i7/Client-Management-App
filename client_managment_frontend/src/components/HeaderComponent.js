import React from "react";

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: FooterComponent is a React component that renders the footer of the application. It includes a link to the author's GitHub profile.
 * @Version: 2.0.0
 */

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