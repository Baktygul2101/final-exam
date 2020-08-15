<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <h3>Список мест</h3>

    <div class="row">
        <div class="col">#</div>
        <div class="col">Название</div>
        <div class="col">Описание</div>
        <div class="col">Фото</div>
    </div>
    <#list items as place>
        <a href="/places/place/${place.id}">
            <div class="row">
                <div class="col">${place?counter}</div>
                <div class="col">${place.name}</div>
                <div class="col">${place.description}</div>
            </div>
        </a>
    </#list>
    <#include "partials/pagination.ftlh">
    <a type="button" class="btn btn-primary w-100" href="/places/add-new-place">
        Добавить
    </a>
</@main.renderWith>