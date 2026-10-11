# Ilyos Client

Чёрно-синее Windows-приложение для тренировок в Counter-Strike: Source v34.

## Первый этап
- WPF-интерфейс на .NET 8
- Aim Trainer с кликабельными учебными мишенями
- Reaction Test
- Сборка Windows x64 через GitHub Actions

Приложение пока автономное: оно не читает память игры, не внедряется в игровой процесс и не содержит игрового aim/trigger-бота.

## Сборка
Откройте вкладку Actions в GitHub и запустите workflow **Build Ilyos Client**. После успешной сборки скачайте артефакт `IlyosClient-win-x64`.
