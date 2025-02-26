const themes = [
    {
        background: "#1A1A2E",
        color: "#FFFFFF",
        primaryColor: "#0F3460",
        glassColor: "rgba(255, 255, 255, 0.1)"
    },
    {
        background: "#461220",
        color: "#FFFFFF",
        primaryColor: "#E94560",
        glassColor: "rgba(255, 255, 255, 0.2)"
    },
    {
        background: "#192A51",
        color: "#FFFFFF",
        primaryColor: "#967AA1",
        glassColor: "rgba(255, 255, 255, 0.3)"
    },
    {
        background: "#F7B267",
        color: "#000000",
        primaryColor: "#F4845F",
        glassColor: "rgba(255, 255, 255, 0.4)"
    },
    {
        background: "#F25F5C",
        color: "#000000",
        primaryColor: "#642B36",
        glassColor: "rgba(255, 255, 255, 0.5)"
    },
    {
        background: "#231F20",
        color: "#FFF",
        primaryColor: "#BB4430",
        glassColor: "rgba(255, 255, 255, 0.6)"
    }
];

const setTheme = (theme) => {
    const root = document.querySelector(":root");
    root.style.setProperty("--background", theme.background);
    root.style.setProperty("--color", theme.color);
    root.style.setProperty("--primary-color", theme.primaryColor);
    root.style.setProperty("--glass-color", theme.glassColor);
};

const displayThemeButtons = () => {
    const btnContainer = document.querySelector(".theme-btn-container");

    if (!btnContainer) {
        console.error("Theme button container not found!");
        return;
    }

    themes.forEach((theme) => {
        const div = document.createElement("div");
        div.className = "theme-btn";
        div.style.cssText = `background: ${theme.background}; width: 25px; height: 25px; cursor: pointer; margin: 2px;`;

        div.addEventListener("click", () => {
            setTheme(theme);
            console.log(`Theme set to: ${theme.background}`);
        });

        btnContainer.appendChild(div);
    });
};

document.addEventListener("DOMContentLoaded", displayThemeButtons);
