<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conversor de Unidades</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #d1d5db;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .main-card {
            background: #ffffff;
            width: 620px;
            padding: 45px 50px;
        }
        .form-group {
            margin-bottom: 22px;
        }
        .form-group label {
            display: block;
            font-size: 0.95rem;
            font-weight: bold;
            color: #1a1a1a;
            margin-bottom: 6px;
        }
        
        .select-categoria {
            width: 100%;
            padding: 7px 10px;
            font-size: 0.95rem;
            font-weight: bold;
            color: #2c3e50;
            background-color: #b0c4de;
            border: 1px solid #7a8a9e;
            outline: none;
        }
        
        .input-valor {
            width: 100%;
            padding: 8px 10px;
            font-size: 0.95rem;
            border: 1px solid #7f8c8d;
            background-color: #ffffff;
            outline: none;
        }

        .row-unidades {
            display: flex;
            gap: 20px;
            margin-bottom: 28px;
        }
        .col-unidad {
            flex: 1;
        }
        .select-unidad {
            width: 100%;
            padding: 7px 10px;
            font-size: 0.95rem;
            font-weight: bold;
            color: #2c3e50;
            background-color: #e6ecf2;
            border: 1px solid #9aa7b5;
            outline: none;
        }

        .btn-group {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }
        .btn-convert {
            flex: 1;
            background-color: #27ae60;
            color: #ffffff;
            padding: 12px;
            font-size: 1.1rem;
            font-weight: bold;
            border: none;
            cursor: pointer;
        }
        .btn-clear {
            flex: 1;
            background-color: #95a5a6;
            color: #ffffff;
            padding: 12px;
            font-size: 1.1rem;
            font-weight: bold;
            border: none;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            display: block;
        }

        .result-container {
            margin-top: 10px;
        }
        .result-label {
            font-size: 1.15rem;
            font-weight: bold;
            color: #1a1a1a;
        }
        .result-value {
            font-size: 1.1rem;
            color: #2c3e50;
            margin-top: 6px;
        }
    </style>
</head>
<body>
    <div class="main-card">
        <form action="ConversionServlet" method="POST" id="formConversor">
            <input type="hidden" name="tipoConversion" id="tipoConversionHidden" value="m_cm" />

            <!-- Categoría -->
            <div class="form-group">
                <label>Categoría:</label>
                <select id="categoriaSelect" class="select-categoria" onchange="actualizarUnidades()">
                    <option value="longitud">longitud</option>
                    <option value="temperatura">temperatura</option>
                    <option value="masa">masa</option>
                </select>
            </div>

            <!-- Valor a Convertir -->
            <div class="form-group">
                <label>Valor a Convertir:</label>
                <input type="number" step="any" name="valor" class="input-valor" value="${valor != null ? valor : ''}" required />
            </div>

            <!-- Unidad Origen y Destino -->
            <div class="row-unidades">
                <div class="col-unidad">
                    <div class="form-group" style="margin-bottom:0;">
                        <label>Unidad Origen:</label>
                        <select id="origenSelect" class="select-unidad" onchange="sincronizarTipo()">
                        </select>
                    </div>
                </div>
                <div class="col-unidad">
                    <div class="form-group" style="margin-bottom:0;">
                        <label>Unidad Destino:</label>
                        <select id="destinoSelect" class="select-unidad" onchange="sincronizarTipo()">
                        </select>
                    </div>
                </div>
            </div>

            <!-- Botones -->
            <div class="btn-group">
                <button type="submit" class="btn-convert">Convertir</button>
                <a href="conversion.jsp" class="btn-clear">Limpiar</a>
            </div>
        </form>

        <div class="result-container">
            <div class="result-label">Resultado:</div>
            <% 
                String resultado = (String) request.getAttribute("resultado");
                if (resultado != null) {
            %>
                <div class="result-value"><%= resultado %></div>
            <% } %>
        </div>
    </div>

    <script>
        const opcionesMap = {
            longitud: {
                origen: [
                    {val: 'm', text: 'Metros (m)'},
                    {val: 'cm', text: 'Centímetros (cm)'},
                    {val: 'km', text: 'Kilómetros (km)'},
                    {val: 'in', text: 'Pulgadas (in)'},
                    {val: 'ft', text: 'Pies (ft)'}
                ],
                destino: [
                    {val: 'cm', text: 'Centímetros (cm)'},
                    {val: 'm', text: 'Metros (m)'},
                    {val: 'km', text: 'Kilómetros (km)'},
                    {val: 'in', text: 'Pulgadas (in)'},
                    {val: 'ft', text: 'Pies (ft)'}
                ]
            },
            temperatura: {
                origen: [
                    {val: 'C', text: 'Celsius (°C)'},
                    {val: 'F', text: 'Fahrenheit (°F)'},
                    {val: 'K', text: 'Kelvin (K)'},
                    {val: 'R', text: 'Rankine (R)'}
                ],
                destino: [
                    {val: 'F', text: 'Fahrenheit (°F)'},
                    {val: 'C', text: 'Celsius (°C)'},
                    {val: 'K', text: 'Kelvin (K)'},
                    {val: 'R', text: 'Rankine (R)'}
                ]
            },
            masa: {
                origen: [
                    {val: 'kg', text: 'Kilogramos (kg)'},
                    {val: 'g', text: 'Gramos (g)'},
                    {val: 'lb', text: 'Libras (lb)'},
                    {val: 'oz', text: 'Onzas (oz)'},
                    {val: 't', text: 'Toneladas (t)'}
                ],
                destino: [
                    {val: 'lb', text: 'Libras (lb)'},
                    {val: 'kg', text: 'Kilogramos (kg)'},
                    {val: 'g', text: 'Gramos (g)'},
                    {val: 'oz', text: 'Onzas (oz)'},
                    {val: 't', text: 'Toneladas (t)'}
                ]
            }
        };

        function actualizarUnidades() {
            const cat = document.getElementById('categoriaSelect').value;
            const origenSelect = document.getElementById('origenSelect');
            const destinoSelect = document.getElementById('destinoSelect');

            origenSelect.innerHTML = '';
            destinoSelect.innerHTML = '';

            opcionesMap[cat].origen.forEach(op => {
                origenSelect.add(new Option(op.text, op.val));
            });
            opcionesMap[cat].destino.forEach(op => {
                destinoSelect.add(new Option(op.text, op.val));
            });

            sincronizarTipo();
        }

        function sincronizarTipo() {
            const origen = document.getElementById('origenSelect').value;
            const destino = document.getElementById('destinoSelect').value;
            document.getElementById('tipoConversionHidden').value = origen + '_' + destino;
        }

        window.onload = function() {
            actualizarUnidades();
            
            const prevTipo = "${tipoSeleccionado}";
            
            if (prevTipo && prevTipo.includes('_')) {
                const partes = prevTipo.split('_');
                const o = partes[0];
                const d = partes[1];
                
                if (['m', 'cm', 'km', 'in', 'ft'].includes(o)) {
                    document.getElementById('categoriaSelect').value = 'longitud';
                } else if (['C', 'F', 'K', 'R'].includes(o)) {
                    document.getElementById('categoriaSelect').value = 'temperatura';
                } else if (['kg', 'g', 'lb', 'oz', 't'].includes(o)) {
                    document.getElementById('categoriaSelect').value = 'masa';
                }
                
                actualizarUnidades();
                
                document.getElementById('origenSelect').value = o;
                document.getElementById('destinoSelect').value = d;
                
                sincronizarTipo();
            }
        };
    </script>
</body>
</html>