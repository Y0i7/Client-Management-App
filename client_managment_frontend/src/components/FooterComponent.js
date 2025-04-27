import React from "react";

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: FooterComponent is a React component that renders the footer of the application. It includes a link to the author's GitHub profile.
 * @Version: 2.0.0
 */

export const FooterComponent = () => {

    return (
        <div>
            <footer className="footer">
                <span className="text-muted">
                    Made By:
                </span>
                <a href="https://github.com/Y0i7">Y0I7</a>
            </footer>
        </div>
    )
}

export default FooterComponent;