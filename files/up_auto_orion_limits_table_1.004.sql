USE	FUIB_APP
GO
IF	object_id('dbo.up_SetVersion')	IS NOT NULL
	EXEC	dbo.up_SetVersion
		@TaskCode	= 'up_auto_orion_limits_table'
	,	@Build		= 4
	,	@Version	= '1.004 from 20.02.2019'
	,	@TaskName	= 'up_auto_orion_limits_table'
	,	@Note		= 'RM-9740'
	,	@Label		= ''
	,	@Project	= 'orion'
GO




CREATE   PROCEDURE [dbo].up_auto_orion_limits_table 
(
                @OP                 TInt,       -- OP=0 - установить значения Old полей
                                                -- OP=1 - загрузить данные (из ДВХ)
                                                -- OP=2 - вернуть макс. дату загрузки
                                                -- OP=55 - обновить после миграции 2625-2620
                                                --------------------------------------------- 
                @Id                 TBigInt   = NULL, -- ИД записи
                @AvailCredLimOld    TMoney    = NULL, -- 
                @AvailCashLimOld    TMoney    = NULL, -- 
                @AccountProfileOld  TCode       = NULL  --
                
)
AS
--==================================================================================================================================
-- Складчиков В.А.
-- 04,11,2016
-- ХП которая работает с таблицей tb_auto_orion_limits
-- EXEC FUIB_APP.dbo.up_auto_orion_limits_table  @OP = 0, @Id = 6
--==================================================================================================================================

  SET NOCOUNT ON
  SET XACT_ABORT ON
BEGIN 
--получить необработаные операции
 IF @OP = 0
 BEGIN
   IF EXISTS (select TOP 1 1 
                from dbo.tb_auto_orion_limits WITH (NOLOCK)
               where Id = @Id)
   BEGIN
     update dbo.tb_auto_orion_limits
        set AvailCredLimOld = @AvailCredLimOld,
            AvailCashLimOld = @AvailCashLimOld,
            AccountProfileOld = @AccountProfileOld
      where Id = @Id
   END
   ELSE
   BEGIN
     RAISERROR ('Запись не найдена',16,1)
     RETURN;
   END
 END
 ELSE IF @OP = 1
 BEGIN

   insert into dbo.tb_auto_orion_limits
                   (ObjId,
                    AccountId,
                    AccountCode,
                    AvailCredLim,
                    AvailCashLim,
                    Created,
                    Changed,
                    Inserted)
    select l.Id,
           l.AccountId,
           l.AccountCode,
           l.AvailCredLim,
           l.AvailCashLim,
           l.Created,
           GETDATE(),
           GETDATE()
      from ##tb_auto_orion_limits l
      left join 
           tb_auto_orion_limits ll
        on ll.ObjId = l.Id
     where ll.Id is null;
     
 END;
 ELSE IF @OP = 2
 BEGIN
   select convert(varchar(23),ISNULL(max(Inserted), '1900-01-01 00:00:00.001'), 121) as Inserted
     from dbo.tb_auto_orion_limits l;
     
 END

 IF @OP = 55
 BEGIN
    -- https://support/WorkOrder.do?woMode=viewWO&woID=792583

    declare @kind_2625_2620 int = (SELECT Id  FROM  scrooge.dbo.LinkKInd WHERE  Code ='ChangeAccounts 2625  to  2620')

    UPDATE o_lim
       SET 
           o_lim.AccountId = old_for.AmountId
      from 
           FUIB_APP..tb_auto_orion_limits    as o_lim
      join scrooge.dbo.AmountLinks           as old_for
        on old_for.Kind             =  @kind_2625_2620 --3000368 
       and old_for.OwnerId          =  o_lim.AccountId
      join scrooge..Accounts                 as acc
        on acc.id =  o_lim.AccountId
       and acc.TransFlag &35  = 0 
    
    -- we must have (createindex Ix_accn_id on FUIB_APP..tb_auto_orion_limits(AccountId)) 
 END



END
--- end of proc (up_auto_orion_limits_table)


GO 
 GRANT	 EXECUTE	
ON	up_auto_orion_limits_table
 TO	fuib_wesb, fuib_inhs; 
 GO