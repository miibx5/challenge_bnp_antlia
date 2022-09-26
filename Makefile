BACK_END_APPLICATION_CONTAINER_NAME=movement-backend-application
FRONT_END_APPLICATION_CONTAINER_NAME=movement-frontend-application
########### CONFIGURATION CORE APP ###############################
BACKEND_PROJECT_DIRECTORY=backend/.
$(BACKEND_PROJECT_DIRECTORY):
	$(MAKE) -C $@
.PHONY: $(CORE_APP_DIRECTORY)
############# CONFIGURATION UI APP ###############################
FRONTEND_PROJECT_DIRECTORY=frontend/.
$(FRONTEND_PROJECT_DIRECTORY):
	$(MAKE) -C $@
.PHONY: $(FRONTEND_PROJECT_DIRECTORY)

build-backend: $(BACKEND_PROJECT_DIRECTORY)
build-fronted: $(FRONTEND_PROJECT_DIRECTORY)
down:
	@docker-compose down --v
run:
	@$(MAKE) down
	@$(MAKE) build-backend
	@$(MAKE) build-fronted
	docker-compose up -d --build
	docker logs ${BACK_END_APPLICATION_CONTAINER_NAME} --follow
