USE	FUIB_APP
GO
IF	object_id('dbo.up_SetVersion')	IS NOT NULL
	EXEC	dbo.up_SetVersion
		@TaskCode	= 'up_auto_orion_confirmation_orchestrator_orig'
	,	@Build		= 1
	,	@Version	= '1.000 from 26.11.2019'
	,	@TaskName	= 'Init from svn to git up_auto_orion_confirmation_orchestrator_orig'
	,	@Note		= 'RM-'
	,	@Label		= 'Git Init'
	,	@Project	= 'orion'
GO



CREATE	OR ALTER PROCEDURE dbo.up_auto_orion_confirmation_orchestrator_orig
	@OperationTypeID		TInt		-- тип операции (tb_auto_orion_operation_type)
AS
/*
https://jira.fuib.com/browse/VSIRIUS-1740
*/
BEGIN
	SET	NOCOUNT ON;
	SET	XACT_ABORT ON;

	EXEC	FUIB_APP.dbo.up_change_context_info	
		@ProcedureName			= 'up_auto_orion_confirmation_orchestrator_orig'

	--параметр кол-ва попыток обработки записи
	DECLARE	@Error			TInt	= 1
	,	@ParamNerror		TCode	= 'Nerror'
	,	@Day			TInt
	,	@ParamDay		TCode	= 'Day'
	,	@Month			TInt
	,	@ParamMonth		TCode	= 'Month';

	--проверим наличие такой операции
	IF	NOT EXISTS 
	(
		SELECT	TOP 1 1 
		FROM	FUIB_APP.dbo.tb_auto_orion_operation_type
		WHERE	id			= @OperationTypeID
	)
	BEGIN
		GOTO	ERR_PROC;
	END;

	-- получим сколько раз нужно обрабатывать запись реестра
	SELECT	@Error				= ISNULL(CAST(paramValue as Int),1)
	FROM	FUIB_APP.dbo.tb_auto_orion_params
	WHERE	OperationTypeId			= @OperationTypeID
	AND	ParamName			= @ParamNerror;

	SELECT	@Day				= ISNULL(CAST(paramValue as Int), NULL)
	FROM	FUIB_APP.dbo.tb_auto_orion_params
	WHERE	OperationTypeId			= @OperationTypeID
	AND	ParamName			= @ParamDay;

	SELECT	@Month				= ISNULL(CAST(paramValue as Int),NULL)
	FROM	FUIB_APP.dbo.tb_auto_orion_params
	WHERE	OperationTypeId			= @OperationTypeID
	AND	ParamName			= @ParamMonth;

	IF	@OperationTypeID	IN (1,2,3)		-- тип операции (tb_auto_orion_operation_type) 
	BEGIN                                     
		IF	@OperationTypeID	= 1
		BEGIN
			IF	EXISTS 
			(
				SELECT	top 1 1
				FROM	FUIB_APP.dbo.tb_auto_orion_limits l 
				LEFT	OUTER
				JOIN	FUIB_APP.dbo.tb_auto_orion_operations p
					ON	p.ObjId			= l.ObjId
					AND	p.OperationTypeId	= @OperationTypeID
				WHERE 
				(
					(
						p.Id		IS NULL
					) 
					OR 
					(
						p.Id		IS NOT NULL
						AND
						p.Status	= 2 
						AND
						p.NError	< @Error
					)
				)       
				AND
				(
					(
						@Day		= 0 
						AND
						@Month		= 0 
					)
					OR
					(
						@Day		= DATEPART(dd,GETDATE()) and DATEPART(MM, DATEADD(MM, @Month, GETDATE())) = ISNULL(DATEPART(MM, l.Created),0)
					)
				)
			)
			BEGIN
				GOTO	END_PROC;
			END;
		END;
		ELSE 
			IF	@OperationTypeID	= 2
			BEGIN
				IF	EXISTS 
				(
					SELECT	TOP 1 1
					FROM	FUIB_APP.dbo.tb_auto_orion_limits l 
					JOIN	FUIB_APP.dbo.tb_auto_orion_operations p
						ON	p.ObjId			= l.ObjId
						AND	p.OperationTypeId	= 1
						AND	p.Status		= 1 
						AND	l.AvailCashLim		> 0	  --снято ограничение на снятие наличных   	    		
					LEFT	OUTER
					JOIN	FUIB_APP.dbo.tb_auto_orion_operations pp
						ON	pp.ObjId		= l.ObjId
						AND	pp.OperationTypeId	= @OperationTypeID	
					WHERE 
					(
						(
							pp.Id			IS NULL
						) 
						OR
						(
							pp.Id			IS NOT NULL
							AND
							pp.Status		= 2 
							AND
							pp.NError		< @Error
						) 
					)
					AND
					(
						(
							@Day			= 0 
							AND
							@Month			= 0 
						)
						OR 
						(
							@Day			= DATEPART(dd,GETDATE()) and DATEPART(MM, DATEADD(MM, @Month, GETDATE())) = ISNULL(DATEPART(MM, p.Created),0)
						)
					)
				)
				BEGIN
					GOTO	END_PROC;
				END;
			END;
			ELSE 
				IF	@OperationTypeID	= 3
				BEGIN
					IF	EXISTS 
					(
						SELECT TOP 1 1
						FROM	FUIB_APP.dbo.tb_auto_orion_limits l 
						JOIN	FUIB_APP.dbo.tb_auto_orion_operations p
							ON	p.ObjId			= l.ObjId
							AND	p.OperationTypeId	= 1
							AND	p.Status		= 1 		
							AND	l.AvailCashLim		> 0	  --CME-4201
						LEFT	OUTER
						JOIN	FUIB_APP.dbo.tb_auto_orion_operations pp
							ON	pp.ObjId		= l.ObjId
							AND	pp.OperationTypeId	= @OperationTypeID	
						WHERE 
						(
							(
								pp.Id			IS NULL
							) 
							OR
							(
								pp.Id			IS NOT NULL
								AND
								pp.Status		= 2 
								AND
								pp.NError		< @Error
							) 
						)
						AND
						(
							(
								@Day			= 0 
								AND
								@Month			= 0 
							)
							OR
							(
								@Day			= DATEPART(dd,GETDATE()) and DATEPART(MM, DATEADD(MM, @Month, GETDATE())) = ISNULL(DATEPART(MM, p.Created),0)
							)
						)
					)
					BEGIN
						GOTO	END_PROC;
					END;
				END;
	END;

ERR_PROC:	
	-- иначе говорим что не работаем
	SELECT	'NO'			AS OP;

	EXEC	FUIB_APP.dbo.up_change_context_info;
	RETURN;

END_PROC:
	SELECT	'YES'			AS OP;

	EXEC	FUIB_APP.dbo.up_change_context_info;
	RETURN;

END;
GO 
 GRANT	 EXECUTE	
ON	up_auto_orion_confirmation_orchestrator_orig
 TO	fuib_wesb, fuib_inhs; 
 GO