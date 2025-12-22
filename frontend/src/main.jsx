import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import App from "./App.jsx";
import { RoomCacheProvider } from "./RoomCacheContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RoomCacheProvider>
      <App />
    </RoomCacheProvider>
  </StrictMode>
);
