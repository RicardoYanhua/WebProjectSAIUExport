$(document).ready(function () {
            // Cargar los departamentos desde el servidor
            $.get('/Ubicacion/getDepartamentos', function (data) {
                const departamentosSelect = $('#departamentosSelect');
                departamentosSelect.empty();
                departamentosSelect.append('<option disabled selected value="">Seleccione una opción...</option>');

                // Rellenar los departamentos en el selector
                data.forEach(function (departamento) {
                    departamentosSelect.append(`<option value="${departamento.id}">${departamento.name}</option>`);
                });

                // Seleccionar el departamento previamente seleccionado
                const idDepartamentoSeleccionado = $('#idDepartamentoSeleccionado').val();
                if (idDepartamentoSeleccionado) {
                    departamentosSelect.val(idDepartamentoSeleccionado).change();

                    // Cargar las provincias correspondientes
                    $.get('/Ubicacion/getProvincias', { ID: idDepartamentoSeleccionado }, function (provinciasData) {
                        const provinciasSelect = $('#provinciasSelect');
                        provinciasSelect.empty();
                        provinciasSelect.append('<option disabled selected value="">Seleccione una opción...</option>');

                        // Rellenar las provincias
                        provinciasData.forEach(function (provincia) {
                            provinciasSelect.append(`<option value="${provincia.id}">${provincia.name}</option>`);
                        });

                        // Seleccionar la provincia previamente seleccionada
                        const idProvinciaSeleccionada = $('#idProvinciaSeleccionado').val();
                        if (idProvinciaSeleccionada) {
                            provinciasSelect.val(idProvinciaSeleccionada).change();

                            // Cargar los distritos correspondientes
                            $.get('/Ubicacion/getDistritos', { ID: idProvinciaSeleccionada }, function (distritosData) {
                                const distritosSelect = $('#distritosSelect');
                                distritosSelect.empty();
                                distritosSelect.append('<option disabled selected value="">Seleccione una opción...</option>');

                                // Rellenar los distritos
                                distritosData.forEach(function (distrito) {
                                    distritosSelect.append(`<option value="${distrito.id}">${distrito.name}</option>`);
                                });

                                // Seleccionar el distrito previamente seleccionado
                                const idDistritoSeleccionado = $('#idDistritoSeleccionado').val();
                                if (idDistritoSeleccionado) {
                                    distritosSelect.val(idDistritoSeleccionado);
                                }
                            }).fail(function () {
                                alert('Error al cargar los distritos.');
                            });
                        }
                    }).fail(function () {
                        alert('Error al cargar las provincias.');
                    });
                }
            }).fail(function () {
                alert('Error al cargar los departamentos.');
            });

            // Actualizar las provincias al seleccionar un departamento
            $('#departamentosSelect').change(function () {
                const departamentoId = $(this).val();

                if (departamentoId) {
                    $.get('/Ubicacion/getProvincias', { ID: departamentoId }, function (data) {
                        const provinciasSelect = $('#provinciasSelect');
                        provinciasSelect.empty();
                        provinciasSelect.append('<option disabled selected value="">Seleccione una opción...</option>');
                        data.forEach(function (provincia) {
                            provinciasSelect.append(`<option value="${provincia.id}">${provincia.name}</option>`);
                        });

                        // Reiniciar los distritos
                        $('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
                    }).fail(function () {
                        alert('Error al cargar las provincias.');
                    });
                } else {
                    // Limpiar provincias y distritos si no hay selección
                    $('#provinciasSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
                    $('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
                }
            });

            // Actualizar los distritos al seleccionar una provincia
            $('#provinciasSelect').change(function () {
                const provinciaId = $(this).val();

                if (provinciaId) {
                    $.get('/Ubicacion/getDistritos', { ID: provinciaId }, function (data) {
                        const distritosSelect = $('#distritosSelect');
                        distritosSelect.empty();
                        distritosSelect.append('<option disabled selected value="">Seleccione una opción...</option>');
                        data.forEach(function (distrito) {
                            distritosSelect.append(`<option value="${distrito.id}">${distrito.name}</option>`);
                        });
                    }).fail(function () {
                        alert('Error al cargar los distritos.');
                    });
                } else {
                    // Limpiar distritos si no hay selección
                    $('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
                }
            });
        });