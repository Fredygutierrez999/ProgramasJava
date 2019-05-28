package compiladorffl;

import java.util.List;
import java.util.*;
import javax.swing.JList;

/**
 * Clase utilizada para compilar listado de string con el código fuente. Esta
 * clase se ejecuta por medio de un Thread
 *
 * @author fredy Gamer
 */
public class compilador extends Thread {

    /**
     * Clase utilizada para retornar objeto con el proceso realizado en la
     * compilación
     */
    public class respuestaProceso {

        private boolean _resultado;
        private List<String> _mensajeProceso;

        public respuestaProceso() {
            this._mensajeProceso = new ArrayList<>();
        }

        /**
         * Retorna el estado del proceso realizado
         *
         * @return
         */
        public boolean getresultado() {
            return _resultado;
        }

        /**
         * Asigna resultado del proceso
         *
         * @param xResultado
         */
        public void setresultado(boolean xResultado) {
            this._resultado = xResultado;
        }

        /**
         * evaluar si existe un mensaje cargado en la lista y retorna el objeto
         * en base a esta evaluación
         */
        public void evaluarResultado() {
            this._resultado = !this._mensajeProceso.isEmpty();
        }

        /**
         * Retorna el mensaje de error generado
         *
         * @return
         */
        public List<String> getMensajes() {
            return this._mensajeProceso;
        }

        /**
         * Retorna el mensaje de error generado
         *
         * @return
         */
        public String getMensajesTexto() {
            String xCadena = "";
            xCadena = this._mensajeProceso.stream().map((xMensaje) -> xMensaje).reduce(xCadena, String::concat);
            return xCadena;
        }

        public void agregarMensaje(String XError) {
            this._mensajeProceso.add(XError);
        }

    }

    /**
     * Pasos realizados por el compilador
     */
    private class pasos {

        private int _linea;
        private List<String> _palabrasReservadas;
        private List<String> _nombre;
        private int _EspecialInicial;
        private String _EspecialComparacion;
        private int _EspecialIncremento;
        private int _EspecialLineaReinicio;
        private int _EspecialLineaFinal;
        private boolean _Asignado;
        private int _Iterado;
        private boolean _Iniciado;

        /**
         * Constructor
         */
        public pasos() {
            this._linea = -1;
            this._palabrasReservadas = new ArrayList<String>();
            this._nombre = new ArrayList<String>();
            this._EspecialInicial = 0;
            this._EspecialComparacion = "";
            this._EspecialIncremento = 0;
            this._EspecialLineaReinicio = 0;
            this._EspecialLineaFinal = 0;
            this._Asignado = false;
        }

        /**
         * Asinar Linea de comando
         *
         * @param xLinea
         */
        public void setLinea(int xLinea) {
            this._linea = xLinea;
        }

        /**
         * Retorna linea
         *
         * @return
         */
        public int getLinea() {
            return this._linea;
        }

        /**
         * agrega palabra reservada del lenguaje
         *
         * @param xPalabra
         */
        public void agregaPalabraReservada(String xPalabra) {
            this._palabrasReservadas.add(xPalabra);
        }

        /**
         * Retorna palabras reservadas del sistema
         *
         * @return
         */
        public List<String> getPalabrasReservada() {
            return this._palabrasReservadas;
        }

        /**
         * Retorna primer nombre
         *
         * @return
         */
        public String primerNombre() {
            return this._nombre.get(0);
        }

        /**
         * Retorna primer palabra reservada
         *
         * @return
         */
        public String primerPalabraReservada() {
            return this._palabrasReservadas.get(0);
        }

        /**
         * Retorna segunda palabra reservada
         *
         * @return
         */
        public String segundaPalabraReservada() {
            return this._palabrasReservadas.get(1);
        }

        /**
         * agrega nombre de la linea (Nombre variables, Programa, etc)
         *
         * @param xNombre
         */
        public void agregaNombre(String xNombre) {
            this._nombre.add(xNombre);
        }

        /**
         * Retorna nombres creados en la linea
         *
         * @return
         */
        public List<String> getNombres() {
            return this._nombre;
        }

        /**
         * Asigna linea si el proceso es verdadero
         *
         * @param xIDEspcial
         */
        public void setEspecialInicial(int xIDEspcial) {
            this._EspecialInicial = xIDEspcial;
        }

        /**
         * Retorna especial incial
         *
         * @return
         */
        public int getEspecialInicial() {
            return this._EspecialInicial;
        }

        /**
         * Asigna campo de comparación
         *
         * @param xEspecialComparacion
         */
        public void setEspecialComparacion(String xEspecialComparacion) {
            this._EspecialComparacion = xEspecialComparacion;
        }

        /**
         * Retorna especial comparacion
         *
         * @return
         */
        public String getEspecialComparacion() {
            return this._EspecialComparacion;
        }

        /**
         * Asigna incremento
         *
         * @param xEspecialIncremento
         */
        public void setIncremento(int xEspecialIncremento) {
            this._EspecialIncremento = xEspecialIncremento;
        }

        /**
         * Retorna incremento de un ciclo
         *
         * @return
         */
        public int getIncremento() {
            return this._EspecialIncremento;
        }

        /**
         * Asigna incremento
         *
         * @param xEspecialIncremento
         */
        public void setIterado(int xIterado) {
            this._Iterado = xIterado;
        }

        /**
         * Retorna incremento de un ciclo
         *
         * @return
         */
        public int getIterado() {
            return this._Iterado;
        }

        /**
         * Asigna linea final si el proceso es falso
         *
         * @param xIDEspcial
         */
        public void setEspecialFinal(int xIDEspcial) {
            this._EspecialLineaFinal = xIDEspcial;
        }

        /**
         * Retorna especial final
         *
         * @return
         */
        public int getEspecialFinal() {
            return this._EspecialLineaFinal;
        }

        /**
         * Marca el paso como ya asignado, utilizado en el if y while
         *
         * @param xAsignado
         */
        public void setAsignado(boolean xAsignado) {
            this._Asignado = xAsignado;
        }

        /**
         * Obtiene el paso como ya asignado, utilizado en el if y while
         *
         * @param xAsignado
         */
        public boolean getAsignado() {
            return this._Asignado;
        }

        /**
         * Marca el paso como ya iniciado
         *
         * @param xAsignado
         */
        public void setIniciado(boolean xIniciado) {
            this._Iniciado = xIniciado;
        }

        /**
         * Obtiene el estado del paso
         *
         * @param xAsignado
         */
        public boolean getIniciado() {
            return this._Iniciado;
        }
    }

    /**
     * Clase utilizada para crear arbol de operadores
     */
    public static class nodo {

        public String valor;
        public nodo izquierda;
        public nodo derecha;
    }

    /**
     * Clase utilizada para almacenar valores de variables
     */
    private class variable {

        private String _tipoDato;
        private Object _valor;
        private String _nombre;

        /**
         * Asigna tipo a la variable
         *
         * @param xTipoDato
         */
        public void setTipoDato(String xTipoDato) {
            if (this._valor == null) {
                switch (xTipoDato) {
                    case "entero":
                        this._valor = "0";
                        break;
                    case "real":
                        this._valor = "0.0";
                        break;
                    case "texto":
                        this._valor = "";
                        break;
                    case "caracter":
                        this._valor = "";
                        break;
                    case "logico":
                        this._valor = "false";
                        break;
                }
            }
            this._tipoDato = xTipoDato;
        }

        /**
         * Obtiene tipo de la variables
         *
         * @return
         */
        public String getTipoDato() {
            return this._tipoDato;
        }

        /**
         * Asigna valor a la variable
         *
         * @param xValor
         */
        public void setValor(Object xValor) {
            this._valor = xValor;
        }

        /**
         * Obtiene valor de la variable
         *
         * @return
         */
        public Object getValor() {
            return this._valor;
        }

        /**
         * Asigna nombre de variable
         *
         * @param xTipoDato
         */
        public void setNombre(String xNombre) {
            this._nombre = xNombre;
        }

        /**
         * Obtiene el nombre de la variable
         *
         * @return
         */
        public String getNombre() {
            return this._nombre;
        }
    ;

    }
    
    /**
     * Clase utilizad para llenar listado de datos en listas
     */
    private class modeloListados extends javax.swing.AbstractListModel {

        private final List<String> lstLineas;

        public modeloListados() {
            this.lstLineas = new ArrayList<>();
        }

        public void agregarLinea(String xLinea) {
            this.lstLineas.add(xLinea);
        }

        @Override
        public int getSize() {
            return this.lstLineas.size();
        }

        @Override
        public Object getElementAt(int i) {
            return lstLineas.get(i);
        }
    }

    private final String _ConstOperadores = ",+,-,*,/,^,&&,||,<<,>>,<=,==,<>,>=,(,),";
    private final String _ConstLetras = "abcdefghijklmnñopqrstuvwxyz";
    private final String _ConstNumeros = "1234567890";
    private final String _ConstOperadoresMatematicas = "+-*/^";
    private final String _ConstOperadoresLogicas = "==,<<,>>,<=,>=,&&,||,<>";
    private final Dictionary<String, variable> _DiccionarioVariables;
    private final Dictionary<String, Object> _DiccionarioTiposDatos;
    private final ArrayList<pasos> _ListPasosCompilado;
    private final modeloListados _ModelLogConsola;
    private final modeloListados _ModelLogErrores;
    private List<String> _ListPasosCompilador;
    private final fmrCompilador _hiloPrincipal;
    private listaCapturaDato _CapturaDatos;
    private listaCapturaDato xLineaConsola;
    private boolean _soloValida;

    /**
     * Marca la bandera solo valida para que el compilador muestre los errores
     * previos
     *
     * @param xvalida
     */
    public void setSoloValidar(boolean xvalida) {
        this._soloValida = xvalida;
    }

    /**
     * Cambia listado de pasos del compilador
     *
     * @param _PasosCompilador
     */
    public void setPasosCompilador(List<String> _PasosCompilador) {
        this._ListPasosCompilador = _PasosCompilador;
    }

    /**
     * Captura datos del usuario, genera un wait miestras se cargan los datos
     *
     * @param xCapturaDatos
     */
    public void setCapturaDatos(listaCapturaDato xCapturaDatos) {
        this._CapturaDatos = xCapturaDatos;
    }

    /**
     * Constructor
     *
     * @param xhiloPrincipal Recibe objeto de tipo fmrCompilador
     * @param xLineaConsola Objeto utilizado para capturar datos de línea de
     * consola
     */
    public compilador(fmrCompilador xhiloPrincipal, listaCapturaDato xLineaConsola) {
        this._hiloPrincipal = xhiloPrincipal;
        this.xLineaConsola = xLineaConsola;

        this._DiccionarioVariables = new Hashtable<>();
        this._ListPasosCompilado = new ArrayList<>();
        this._DiccionarioTiposDatos = new Hashtable<>();

        /**
         * Carga tipos de datos
         */
        this._DiccionarioTiposDatos.put("entero", "entero");
        this._DiccionarioTiposDatos.put("real", "real");
        this._DiccionarioTiposDatos.put("texto", "texto");
        this._DiccionarioTiposDatos.put("caracter", "caracter");
        this._DiccionarioTiposDatos.put("logico", "logico");
        this._ModelLogConsola = new modeloListados();
        this._ModelLogErrores = new modeloListados();
    }

    /**
     * Carga listado de cadena de mensajes a mostrar
     *
     * @param xCadena String con cadena a mostrar
     * @return
     */
    public List<String> cargarCadenaMensaje(String xCadena) {
        List<String> lstPartes = new ArrayList<>();
        String xCadenaTmp = "";
        Boolean xEnCadena = false;
        Boolean xEnCadenaComa = false;
        for (int i = 0; i < xCadena.length(); i++) {

            if (xCadena.charAt(i) == '"') {
                xCadenaTmp += "\"";
                if (xEnCadena == true) {
                    lstPartes.add(xCadenaTmp);
                    xCadenaTmp = "";
                }
                xEnCadena = !xEnCadena;
            } else {
                if (xEnCadena) {
                    xCadenaTmp += xCadena.charAt(i);
                } else {
                    if (xCadena.charAt(i) == ',') {
                        if (!"".equals(xCadenaTmp)) {
                            lstPartes.add(xCadenaTmp);
                            xCadenaTmp = "";
                        }
                    } else {
                        xCadenaTmp += xCadena.charAt(i);
                    }
                }
            }
        }
        if (!"".equals(xCadenaTmp) && !";".equals(xCadenaTmp)) {
            lstPartes.add(xCadenaTmp.replace(";", ""));
        }
        return lstPartes;
    }

    /**
     * Agrega datos a consola
     *
     * @param xLinea
     */
    private void AgregarConsola(String xLinea) {
        this._ModelLogConsola.agregarLinea(xLinea);
        this._hiloPrincipal.sincroPasoAPaso(_ModelLogConsola);
    }

    /**
     * Agrega datos a consola
     *
     * @param xLinea
     */
    private void AgregarConsolaLn(String xLinea) {
        AgregarConsola(xLinea);
    }

    /**
     * Agrega datos a consola
     *
     * @param xLinea
     */
    private void AgregarConsolaError(String xLinea) {
        this._ModelLogErrores.agregarLinea(xLinea);
        this._hiloPrincipal.sincroErrores(this._ModelLogErrores);
    }

    /**
     * Metodo utilizado para cargar listado de pasos a ejecutar
     *
     * @return
     */
    private respuestaProceso cargarLinearACompilar(List<String> lstCadenas) {
        respuestaProceso objProceso = new respuestaProceso();
        int xLinea = 0;

        /**
         * Recorre lineas
         */
        for (String lstCadena : lstCadenas) {
            if (!"".equals(lstCadena.trim())) {

                String[] arrIntrucciones = lstCadena.trim().toLowerCase().replace(";", "").replace("\t", " ").replace("  ", " ").replace("  ", " ").replace("  ", " ").split(" ");
                pasos objTemporal = new pasos();
                objTemporal.setLinea(xLinea);
                switch (arrIntrucciones[0]) {
                    case "programa":
                        try {
                            if (arrIntrucciones.length == 2) {
                                objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                                objTemporal.agregaNombre(arrIntrucciones[1]);
                            } else {
                                AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "var":
                        try {
                            if (arrIntrucciones.length == 3) {
                                objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                                objTemporal.agregaPalabraReservada(arrIntrucciones[1]);
                                if (this._DiccionarioTiposDatos.get(arrIntrucciones[1]) != null) {
                                    String[] xVariablesTmp = arrIntrucciones[2].split(",");
                                    for (String xVariable : xVariablesTmp) {
                                        objTemporal.agregaNombre(xVariable.trim());
                                    }
                                } else {
                                    AgregarConsolaError("El tipo de dato no existe.");
                                }
                            } else {
                                AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "escribir":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            List<String> lstPartesCadena = cargarCadenaMensaje(lstCadena.trim().substring(9, lstCadena.trim().length()));
                            for (String lstPartesCadena1 : lstPartesCadena) {
                                objTemporal.agregaNombre(lstPartesCadena1);
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "escribirln":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            List<String> lstPartesCadenaLn = cargarCadenaMensaje(lstCadena.trim().substring(11, lstCadena.trim().length()));
                            for (String lstPartesCadena1 : lstPartesCadenaLn) {
                                objTemporal.agregaNombre(lstPartesCadena1);
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "leer":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            objTemporal.agregaNombre(arrIntrucciones[1]);
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "repetir":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            String[] _PartesCiclo = arrIntrucciones[1].split(":");
                            if (_PartesCiclo.length == 3) {
                                try {
                                    String _Variable = _PartesCiclo[0].split("=")[0];
                                    String _Inicio = _PartesCiclo[0].split("=")[1];
                                    String _Condicion = _PartesCiclo[1];
                                    String _Incremento = _PartesCiclo[2];

                                    objTemporal.agregaNombre(_Variable);
                                    objTemporal.setIterado(Integer.parseInt(_Inicio));
                                    objTemporal.setEspecialComparacion(_Condicion);
                                    objTemporal.setIncremento(Integer.parseInt(_Incremento));
                                    objTemporal.agregaNombre(arrIntrucciones[1]);
                                } catch (Exception ex) {
                                    AgregarConsolaError("Las partes del ciclo son incorrectas, Linea:" + Integer.toString(xLinea));
                                }
                            } else {
                                AgregarConsolaError("Las partes del ciclo son incorrectas, Linea:" + Integer.toString(xLinea));
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "finrepetir":
                        try {
                            boolean existePadre = false;
                            for (int i = this._ListPasosCompilado.size() - 1; i >= 0; i--) {
                                if ("repetir".equals(this._ListPasosCompilado.get(i).primerPalabraReservada()) && this._ListPasosCompilado.get(i).getAsignado() == false) {
                                    this._ListPasosCompilado.get(i).setAsignado(true);
                                    this._ListPasosCompilado.get(i).setEspecialFinal(xLinea);
                                    objTemporal.setEspecialInicial(i);
                                    i = 0;
                                    existePadre = true;
                                }
                            }

                            if (existePadre) {
                                objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            } else {
                                AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", finrepetir no posee el inicio 'repetir'.");
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "si":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            objTemporal.agregaNombre(lstCadena.trim().substring(3, lstCadena.trim().length()));
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "sino":
                        try {
                            boolean existePadreSiNo = false;
                            for (int i = this._ListPasosCompilado.size() - 1; i >= 0; i--) {
                                if ("si".equals(this._ListPasosCompilado.get(i).primerPalabraReservada()) && this._ListPasosCompilado.get(i).getAsignado() == false) {
                                    this._ListPasosCompilado.get(i).setAsignado(true);
                                    this._ListPasosCompilado.get(i).setEspecialFinal(xLinea);
                                    i = 0;
                                    existePadreSiNo = true;
                                }
                            }
                            if (existePadreSiNo) {
                                objTemporal.agregaPalabraReservada(arrIntrucciones[0]);

                            } else {
                                AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", sino sin primer palabra 'si'.");
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "finsi":
                        try {
                            boolean existePadreFinsi = false;
                            for (int i = this._ListPasosCompilado.size() - 1; i >= 0; i--) {
                                if ("sino".equals(this._ListPasosCompilado.get(i).primerPalabraReservada()) && this._ListPasosCompilado.get(i).getAsignado() == false) {
                                    this._ListPasosCompilado.get(i).setAsignado(true);
                                    this._ListPasosCompilado.get(i).setEspecialFinal(xLinea);
                                    i = 0;
                                    existePadreFinsi = true;
                                }
                                if ("si".equals(this._ListPasosCompilado.get(i).primerPalabraReservada()) && this._ListPasosCompilado.get(i).getAsignado() == false) {
                                    this._ListPasosCompilado.get(i).setAsignado(true);
                                    this._ListPasosCompilado.get(i).setEspecialFinal(xLinea);
                                    i = 0;
                                    existePadreFinsi = true;
                                }
                            }
                            if (existePadreFinsi) {
                                objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            } else {
                                AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", finsi sin inicio 'si o sino'.");
                            }
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    case "set":
                        try {
                            objTemporal.agregaPalabraReservada(arrIntrucciones[0]);
                            objTemporal.agregaNombre(lstCadena.trim().substring(3, lstCadena.trim().length()).replace(";", ""));
                        } catch (Exception ex) {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", valide.");
                        }
                        break;
                    default:
                        if (arrIntrucciones[0].substring(0, 2) != "//") {
                            AgregarConsolaError("Error en línea " + Integer.toString(xLinea) + ", instrucción no reconocida.");
                        }
                        break;
                }
                this._ListPasosCompilado.add(objTemporal);
                xLinea++;

            }
        }

        if (this._ModelLogErrores.getSize() == 0) {
            //El programa debe inicial por la palabra reservada programa
            if (!"programa".equals(this._ListPasosCompilado.get(0).primerPalabraReservada())) {
                AgregarConsolaError("El programa debe inicial con la palabra 'Programa'.");
            }

            //valida que todos los ciclos posean un inicio y fin
            for (int i = 0; i < this._ListPasosCompilado.size(); i++) {
                if ("repetir".equals(this._ListPasosCompilado.get(i).primerPalabraReservada())
                        && this._ListPasosCompilado.get(i).getAsignado() == false) {
                    AgregarConsolaError("Error en la lìnea " + Integer.toString(i) + ", el ciclo repetir no posee un fin");
                }
            }
        }

        objProceso.setresultado(this._ModelLogErrores.getSize() == 0);
        return objProceso;
    }

    /**
     * Compila programa
     *
     * @return
     */
    public respuestaProceso compilar() {
        respuestaProceso objProceso = new respuestaProceso();
        for (int i = 0; i < this._ListPasosCompilado.size(); i++) {
            pasos objPaso = this._ListPasosCompilado.get(i);
            switch (objPaso.primerPalabraReservada()) {
                case "programa":
                    AgregarConsolaLn("**************************Inicia programa: " + objPaso.getNombres().get(0) + "**************************");
                    break;
                case "var":
                    for (String nombre : objPaso.getNombres()) {
                        variable objVariable = new variable();
                        objVariable.setNombre(nombre);
                        objVariable.setTipoDato(objPaso.segundaPalabraReservada());
                        this._DiccionarioVariables.put(nombre, objVariable);
                    }
                    break;
                case "escribir":
                    String xCadena = "";
                    for (String nombre : objPaso.getNombres()) {
                        if ("\"".equals(nombre.substring(0, 1))) {
                            xCadena += nombre.replace("\"", "");
                        } else {
                            xCadena += this._DiccionarioVariables.get(nombre).getValor().toString();
                        }
                    }
                    AgregarConsola(xCadena);
                    break;
                case "escribirln":
                    String xCadenaln = "";
                    for (String nombre : objPaso.getNombres()) {
                        if ("\"".equals(nombre.substring(0, 1))) {
                            xCadenaln += nombre.replace("\"", "");
                        } else {
                            xCadenaln += this._DiccionarioVariables.get(nombre).getValor().toString();
                        }
                    }
                    AgregarConsolaLn(xCadenaln);
                    break;
                case "leer":
                    String xValor = "0";
                    try {
                        this._hiloPrincipal.habilitarConsola(true);
                        xValor = xLineaConsola.getDato();
                        this._hiloPrincipal.habilitarConsola(false);
                        variable objTemporal = this._DiccionarioVariables.get(objPaso.primerNombre());
                        if (objTemporal != null) {
                            if (esNumero(objTemporal) || esDecimal(objTemporal)) {
                                try {
                                    Double.parseDouble(xValor);
                                    objTemporal.setValor(xValor);
                                    AgregarConsola(xValor);
                                } catch (Exception ex) {
                                    AgregarConsolaError("La variable " + objPaso.primerNombre() + " solo permite valores númericos.");
                                    i = this._ListPasosCompilado.size();
                                }
                            } else {
                                objTemporal.setValor(xValor);
                                AgregarConsola(xValor);
                            }
                        } else {
                            AgregarConsolaError("La variable " + objPaso.primerNombre() + ",no se ha declarado.");
                        }
                    } catch (InterruptedException ex) {
                        //i = this._ListPasosCompilado.size();
                    }
                    break;
                case "repetir":

                    if (!objPaso.getIniciado()) {
                        variable objVariable = this._DiccionarioVariables.get(objPaso.primerNombre());
                        objVariable.setValor(Integer.toString(objPaso.getIterado() - 1));
                        objPaso.setIniciado(true);
                    }

                    variable objResultadoWhile = evaluarExpresion(objPaso.getEspecialComparacion());
                    if (esBoolean(objResultadoWhile)) {
                        variable objVariable = this._DiccionarioVariables.get(objPaso.primerNombre());
                        if (objResultadoWhile.getValor() == "verdadero") {
                            objVariable.setValor(Integer.toString(Integer.parseInt((String) objVariable.getValor()) + objPaso.getIncremento()));
                        } else {
                            objPaso.setIniciado(false);
                            i = objPaso.getEspecialFinal();
                        }
                    } else {
                        AgregarConsolaError("La operación lógica del ciclo no es una comparación lógica.");
                    }
                    break;
                case "finrepetir":
                    i = objPaso.getEspecialInicial() - 1;
                    break;
                case "si":
                    String xComparacion = objPaso.primerNombre();
                    variable objResultado = evaluarExpresion(xComparacion);
                    if (esBoolean(objResultado)) {
                        if (objResultado.getValor() != "verdadero") {
                            i = objPaso.getEspecialFinal();
                        }
                    } else {
                        AgregarConsolaError("El tipo de operación en el IF no corresponde a una operación lógica.");
                    }
                    break;
                case "sino":
                    i = objPaso.getEspecialFinal();
                    break;
                case "finsi":
                    break;
                case "set":
                    String[] xPartesAsignacio = objPaso.primerNombre().split("=");
                    variable _VariableDestino = this._DiccionarioVariables.get(xPartesAsignacio[0].trim());
                    variable _objResultado = evaluarExpresion(xPartesAsignacio[1].trim());
                    if (_VariableDestino.getTipoDato().equals(_objResultado.getTipoDato())) {
                        _VariableDestino.setValor(_objResultado.getValor());
                    } else {
                        if ("entero".equals(_objResultado.getTipoDato()) && "real".equals(_VariableDestino.getTipoDato())) {
                            _VariableDestino.setValor(_objResultado.getValor());
                        } else if ("entero".equals(_VariableDestino.getTipoDato()) && "real".equals(_objResultado.getTipoDato())) {
                            long _ValorTmp = Long.parseLong((String) _objResultado.getValor());
                            int _ValorEnteroTmp = (int) _ValorTmp;
                            _VariableDestino.setValor(Integer.toString(_ValorEnteroTmp));
                        } else {
                            AgregarConsolaError("El tipo de dato de destino no es correcto.");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return objProceso;
    }

    /**
     * Evento principal encargado de realizar la compilación de las lineas
     *
     * @param lstCadenas
     * @param xTextArea
     * @param xTextErrores
     * @return
     */
    public respuestaProceso compilarCadena(List<String> lstCadenas, JList xTextArea, JList xTextErrores) {
        respuestaProceso objProceso;
        objProceso = cargarLinearACompilar(lstCadenas);
        if (objProceso.getresultado() && this._ModelLogErrores.getSize() == 0) {
            objProceso = compilar();
        }
        return objProceso;
    }

    /**
     * Evalua expresión
     *
     * @param xComparacion
     * @return
     */
    private variable evaluarExpresion(String xComparacion) {
        Stack<String> pila = new Stack<>();
        List<String> cadena = new ArrayList<>();
        Queue<String> cola = new LinkedList<>();

        List<String> xPalabras = FragmentarPalabra("(" + xComparacion + ")", ",+,-,*,/,^,(,),&&,>>,<<,<=,>=,||,==,<>,");

        /*CONVIERTE DE INFIJA A PREFIJO*/
        for (int i = xPalabras.size() - 1; i >= 0; i--) {
            if (_ConstOperadores.contains("," + xPalabras.get(i) + ",")) {
                if ("(".equals(xPalabras.get(i))) {
                    String xUltimoItem = pila.pop();
                    while (!")".equals(xUltimoItem)) {
                        cadena.add(0, "" + xUltimoItem);
                        xUltimoItem = pila.pop();
                    }
                } else {
                    pila.push(xPalabras.get(i));
                }
            } else {
                cadena.add(0, xPalabras.get(i));
            }
        }

        /*INVIERTE CADENA A NOTACIÓN PREFIJA*/
        for (String cadena1 : cadena) {
            cola.add(cadena1);
        }

        /*CREA NODO PADRE Y CARGA NODOS HIJOS*/
        nodo objRoot = new nodo();
        objRoot.valor = cola.poll();
        cargarArbol(objRoot, cola);

        //System.out.println(ImprimirArbol(objRoot));
        variable objResul = evaluar(objRoot);
        return objResul;
    }

    /**
     * Framenta cadena de acuerdo a separadores
     *
     * @param xCadena Cadena a fragmentar
     * @param xSeparadores Separadores de fragmentacion
     * @return
     */
    private List<String> FragmentarPalabra(String xCadena, String xSeparadores) {
        List<String> lstPalabras = new ArrayList<>();
        Queue<String> lstTemporal = new LinkedList<>();
        for (int i = 0; i < xCadena.length(); i++) {

            //Libera cola cuando se encuentra valores enteros
            if ((xSeparadores.contains("," + xCadena.charAt(i) + ",")
                    || xSeparadores.contains("," + xCadena.charAt((i == 0 ? 0 : i - 1)) + xCadena.charAt(i) + ",")) && !lstTemporal.isEmpty()) {
                String xTemporal = "";
                String xIterador = lstTemporal.poll();
                if (!"&|<>=".contains(xIterador)) {
                    xTemporal += xIterador;
                    while (xIterador != null) {
                        xIterador = lstTemporal.poll();
                        if (xIterador != null) {
                            if (!"&|<>=".contains(xIterador)) {
                                xTemporal += xIterador;
                            } else {
                                lstTemporal.add(xIterador);
                                xIterador = null;
                            }
                        }
                    }
                    lstPalabras.add(xTemporal);
                } else {
                    lstTemporal.add(xIterador);
                }
            }

            //agrega caracter acomulativo en pila
            lstTemporal.add("" + xCadena.charAt(i));

            //Libera cola cuando se encuentra separadores
            if ((xSeparadores.contains("," + xCadena.charAt(i) + ",")
                    || xSeparadores.contains("," + xCadena.charAt((i == 0 ? 0 : i - 1)) + xCadena.charAt(i) + ","))) {
                String xTemporal = "";
                String xIterador = lstTemporal.poll();
                xTemporal += xIterador;
                while (xIterador != null) {
                    xIterador = lstTemporal.poll();
                    if (xIterador != null) {
                        xTemporal += xIterador;
                    }
                }
                lstPalabras.add(xTemporal);
            }

        }
        return lstPalabras;
    }

    /**
     * Imprime arbol
     *
     * @param xCabeza
     * @return
     */
    private String ImprimirArbol(nodo xCabeza) {
        if (xCabeza.izquierda == null && xCabeza.derecha == null) {
            return xCabeza.valor;
        } else {
            String xCadena = "(" + ImprimirArbol(xCabeza.izquierda);
            xCadena += xCabeza.valor;
            xCadena += ImprimirArbol(xCabeza.derecha) + ")";
            return xCadena;
        }
    }

    /**
     * Carga arbol con hijos por recursividad
     *
     * @param xCabeza
     * @param xcola
     */
    private void cargarArbol(nodo xCabeza, Queue<String> xcola) {
        String xValor = xcola.poll();
        if (xValor != null) {
            nodo objIzquierda = new nodo();
            objIzquierda.valor = xValor;
            xCabeza.izquierda = objIzquierda;
            if (_ConstOperadores.contains("," + xValor + ",")) {
                cargarArbol(xCabeza.izquierda, xcola);
            }
        }

        xValor = xcola.poll();
        if (xValor != null) {
            nodo objDerecha = new nodo();
            objDerecha.valor = xValor;
            xCabeza.derecha = objDerecha;
            if (_ConstOperadores.contains("," + xValor + ",")) {
                cargarArbol(xCabeza.derecha, xcola);
            }
        }
    }

    /**
     * Crea objeto con el tipo de dato
     *
     * @return
     */
    private variable defineVariable(String xValor) {
        variable objItem = new variable();
        if (!"".equals(xValor.trim())) {
            if (",Verdadero,Falso,".contains("," + xValor + ",")) {
                objItem.setValor(xValor);
                objItem.setTipoDato("logico");
            } else {
                if (_ConstLetras.contains("" + xValor.toLowerCase().charAt(0))) {
                    variable objTemp = this._DiccionarioVariables.get(xValor.toLowerCase());
                    if (objTemp == null) {
                        AgregarConsolaError("La variable " + xValor + ", no existe.");
                        objItem.setValor('0');
                    } else {
                        objItem = objTemp;
                    }
                }
            }
            if (xValor.toLowerCase().charAt(0) == '"') {
                objItem.setValor(xValor);
                objItem.setTipoDato("texto");
            }
            if (xValor.toLowerCase().charAt(0) == '\'') {
                objItem.setValor(xValor);
                objItem.setTipoDato("caracter");
            }
            if (xValor.contains(".")) {
                if (_ConstNumeros.contains("" + xValor.toLowerCase().charAt(0))) {
                    objItem.setValor(xValor);
                    objItem.setTipoDato("real");
                }
            } else {
                if (_ConstNumeros.contains("" + xValor.toLowerCase().charAt(0))) {
                    objItem.setValor(xValor);
                    objItem.setTipoDato("entero");
                }
            }
        } else {
            objItem.setValor(0);
            objItem.setTipoDato("vacio");
        }
        return objItem;
    }

    /**
     * Valida si el objeto variable es numero
     *
     * @param obj
     * @return
     */
    private boolean esNumero(variable obj) {
        return ("real".equals(obj.getTipoDato()) || "entero".equals(obj.getTipoDato()));
    }

    /**
     * Valida si el objeto variable es texto o caracter
     *
     * @param obj
     * @return
     */
    private boolean esCadena(variable obj) {
        return ("texto".equals(obj.getTipoDato()) || "caracter".equals(obj.getTipoDato()));
    }

    /**
     * Valida si el objeto variable es booleano
     *
     * @param obj
     * @return
     */
    private boolean esBoolean(variable obj) {
        return ("logico".equals(obj.getTipoDato()));
    }

    /**
     * Valida si el objeto variable es decimal
     *
     * @param obj
     * @return
     */
    private boolean esDecimal(variable obj) {
        return ("real".equals(obj.getTipoDato()));
    }

    /**
     * Genera una evaluación del arbol de operaciones y calcula un nodo final
     * con el resultado, este metodo se utiliza en intrucciones como if, while y
     * set
     *
     * @param xCabeza
     * @return
     */
    private variable evaluar(nodo xCabeza) {
        if (xCabeza.izquierda == null && xCabeza.derecha == null) {
            return defineVariable(xCabeza.valor);
        } else {
            variable objIzquierda = evaluar(xCabeza.izquierda);
            variable objDerecha = evaluar(xCabeza.derecha);
            variable objRespuesta = new variable();

            long xValorIzq = 0;
            long xValorDer = 0;
            long xValorResultado = 0;

            if (_ConstOperadoresMatematicas.contains(xCabeza.valor)) {
                if (esNumero(objIzquierda) && esNumero(objDerecha)) {
                    if (esDecimal(objIzquierda) || esDecimal(objDerecha)) {
                        double _Resultado = 0;
                        switch (xCabeza.valor) {
                            case "+":
                                _Resultado = Double.parseDouble((String) objIzquierda.getValor()) + Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "-":
                                _Resultado = Double.parseDouble((String) objIzquierda.getValor()) - Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "/":
                                _Resultado = Double.parseDouble((String) objIzquierda.getValor()) / Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "*":
                                _Resultado = Double.parseDouble((String) objIzquierda.getValor()) * Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "^":
                                _Resultado = Math.pow(Double.parseDouble((String) objIzquierda.getValor()), Double.parseDouble((String) objDerecha.getValor()));
                                break;
                            default:
                                _Resultado = 0;
                                break;
                        }
                        objRespuesta.setTipoDato("real");
                        objRespuesta.setValor(Double.toString(_Resultado));
                    } else {
                        long _Resultado = 0;
                        switch (xCabeza.valor) {
                            case "+":
                                _Resultado = Long.parseLong((String) objIzquierda.getValor()) + Long.parseLong((String) objDerecha.getValor());
                                objRespuesta.setTipoDato("entero");
                                objRespuesta.setValor(Long.toString(_Resultado));
                                break;
                            case "-":
                                _Resultado = Long.parseLong((String) objIzquierda.getValor()) - Long.parseLong((String) objDerecha.getValor());
                                objRespuesta.setTipoDato("entero");
                                objRespuesta.setValor(Long.toString(_Resultado));
                                break;
                            case "/":
                                _Resultado = Long.parseLong((String) objIzquierda.getValor()) / Long.parseLong((String) objDerecha.getValor());
                                objRespuesta.setTipoDato("entero");
                                objRespuesta.setValor(Long.toString(_Resultado));
                                break;
                            case "*":
                                _Resultado = Long.parseLong((String) objIzquierda.getValor()) * Long.parseLong((String) objDerecha.getValor());
                                objRespuesta.setTipoDato("entero");
                                objRespuesta.setValor(Long.toString(_Resultado));
                                break;
                            case "^":
                                double _DResultado = Math.pow(Double.parseDouble((String) objIzquierda.getValor()), Double.parseDouble((String) objDerecha.getValor()));
                                objRespuesta.setTipoDato("real");
                                objRespuesta.setValor(Double.toString(_DResultado));
                                break;
                            default:
                                _Resultado = 0;
                                break;
                        }
                    }
                }
            }

            //static String xOperacionesLogicas = "==,&&,||";
            String xOperadoresEntreNumeros = "<<,>>,<=,>=,==,<>";
            String xOperadoresLogicos = "==,&&,||";
            if (_ConstOperadoresLogicas.contains(xCabeza.valor)) {
                if (xOperadoresEntreNumeros.contains(xCabeza.valor)) {
                    boolean xResultado = false;

                    if (esNumero(objIzquierda) && esNumero(objDerecha)) {
                        switch (xCabeza.valor) {
                            case "<<":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) < Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case ">>":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) > Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "<=":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) <= Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case ">=":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) >= Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "==":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) == Double.parseDouble((String) objDerecha.getValor());
                                break;
                            case "<>":
                                xResultado = Double.parseDouble((String) objIzquierda.getValor()) != Double.parseDouble((String) objDerecha.getValor());
                                break;
                        }
                        objRespuesta.setTipoDato("logico");
                        objRespuesta.setValor(xResultado ? "verdadero" : "falso");
                    }

                    if (esCadena(objIzquierda) && esCadena(objDerecha)) {
                        String xIzquierda = (String) objIzquierda.getValor();
                        String xDerecha = (String) objDerecha.getValor();
                        switch (xCabeza.valor) {
                            case "==":
                                xResultado = xIzquierda.equals(xDerecha);
                                break;
                            case "<>":
                                xResultado = !xIzquierda.equals(xDerecha);
                                break;
                        }
                        objRespuesta.setTipoDato("logico");
                        objRespuesta.setValor(xResultado ? "verdadero" : "falso");
                    }

                }

                if (xOperadoresLogicos.contains(xCabeza.valor)) {
                    if (esBoolean(objIzquierda) && esBoolean(objDerecha)) {
                        boolean xResultado = false;
                        switch (xCabeza.valor) {
                            case "&&":
                                xResultado = objIzquierda.getValor() == "verdadero" && objDerecha.getValor() == "verdadero";
                                break;
                            case "||":
                                xResultado = objIzquierda.getValor() == "verdadero" || objDerecha.getValor() == "verdadero";
                                break;
                        }
                        objRespuesta.setTipoDato("logico");
                        objRespuesta.setValor(xResultado ? "verdadero" : "falso");
                    }
                }

            }

            return objRespuesta;
        }
    }

    /**
     * Evento principal encargado de realizar la compilación de las lineas
     */
    @Override
    public void run() {
        respuestaProceso objProceso;
        objProceso = cargarLinearACompilar(this._ListPasosCompilador);
        if (objProceso.getresultado() && this._ModelLogErrores.getSize() == 0) {
            if (!this._soloValida) {
                compilar();
            }
        }
        if (!this._soloValida) {
            AgregarConsola("**************************FIN**************************");
        } else {
            AgregarConsola("**COMPILACIÓN EXITOSA**");
        }
        this._hiloPrincipal.habilitarCompilador(true);
    }

}
