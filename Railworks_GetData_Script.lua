-- Place this file in your railworks plugins folder
-- Usually C:\Program Files (x86)\Steam\steamapps\common\railworks\plugins (for 64 bit windows)
-- or C:\Program Files\Steam\steamapps\common\railworks\plugins (for 32 bit windows)

--The command used to check if a certain control exists or not and if it does
-- retreive its value is:-

-- if Call("*:ControlExists", Name of control, 0) == 1 then
-- variable = Call( "*:GetControlValue", Name of control, 0 )
--end

--This code block as you will see is used throughout the script to retreive
--the data for each control required and can easily be added to or deleted from.
--I have included in the files 'Railworks EngineData.pdf' which lists all the
--control names I have found along with their min/max/default values.
--I then use the local variable data to store the name of the control followed by a colon
-- and the return value and a new line. The data variable is added to as each control is read
-- throughout the script and at the end of the script we open the plugins/GetData.txt file
-- and write the whole data variable to the file ready for reading into your program.

--A word of warning, be careful with your syntax when editing lua script files because
--the only way you know something is wrong is when the script does not work. That is
--why I have included the line gCurrentTime = Call( "*:GetSimulationTime", 0 ). If your
--script is OK then the line at the end of the textbox display in my C# program will
--display "Current Time = :" followed by the time in seconds counting up. If this line
--is static then there is an error in your script. So my advice is make small changes
--and test your script regularly. 

-------------------  GetData function --------------------

function getdata ()
	-- Check the player is driving this train
	if Call("*:GetIsEngineWithKey") == 1 then
		local mpstomph = 2.24 -- * to convert metres per second to mph 
		local MetresToMiles = 0.00062 -- * to convert metres to miles
		local mpstokph = 3.610 -- * to convert metres per second to kph
		local MetresToKilometres = 0.001 -- * to convert metres to kilometres
		local data = "" --Data storage for data to write to output file
		local SpeedoType = "None" -- Is our train using metric (KPH) or imperial (MPH)
		local ThrottleType = ""
		local ThrottleMin = 0
		local ThrottleMax = 0
		local BrakeType = ""

		gCurrentTime = Call( "*:GetSimulationTime", 0 )--Used to show script is running as clock updates in real time
		
		--********** Get Speedo type and current speed **********--
		if Call("*:ControlExists", "SpeedometerMPH", 0) == 1 then
			CurrentSpeed = Call( "*:GetControlValue", "SpeedometerMPH", 0 )
			SpeedoType = "MPH" -- Use MPH
			if (CurrentSpeed > 99.9) then 
				CurrentSpeed = string.format("%d",CurrentSpeed)
			else
				CurrentSpeed = string.format("%3.1f",CurrentSpeed)
			end
		elseif Call("*:ControlExists", "SpeedometerKPH", 0) == 1 then
			CurrentSpeed = Call( "*:GetControlValue", "SpeedometerKPH", 0 )
			SpeedoType = "KPH" -- Use KPH
			if (CurrentSpeed > 99.9) then 
				CurrentSpeed = string.format("%d",CurrentSpeed)
			else
				CurrentSpeed = string.format("%3.1f",CurrentSpeed)
			end
		end			
		data = "Speedo type:"..SpeedoType.."\n"
		
		if SpeedoType ~= "None" then
			data = data.."Current Speed:"..CurrentSpeed.."\n"
		end

		--********** Ascertain throttle type and setting **********--
		if Call("*:ControlExists", "VirtualThrottle", 0) == 1 then
			Throttle = string.format("%d",Call( "*:GetControlValue", "VirtualThrottle", 0 ) * 100)
			ThrottleType = "VirtualThrottle"
			ThrottleMin = string.format("%d",Call( "*:GetControlMinimum", "VirtualThrottle",0))
			ThrottleMax = string.format("%d",Call( "*:GetControlMaximum", "VirtualThrottle", 0 ))
			data = data .."ThrottleType:"..ThrottleType.."\n"
			data = data .."ThrottleMin:"..ThrottleMin.."\n"
			data = data .."ThrottleMax:"..ThrottleMax.."\n"

		elseif Call("*:ControlExists", "ThrottleAndBrake", 0) == 1 then
			Throttle = string.format("%d",Call( "*:GetControlValue", "ThrottleAndBrake", 0 ) * 100)
			ThrottleType = "ThrottleAndBrake"
			ThrottleMin = string.format("%d",Call( "*:GetControlMinimum", "ThrottleAndBrake",0))
			ThrottleMax = string.format("%d",Call( "*:GetControlMaximum", "ThrottleAndBrake", 0 ))
			data = data .."ThrottleType:"..ThrottleType.."\n"
			data = data .."ThrottleMin:"..ThrottleMin.."\n"
			data = data .."ThrottleMax:"..ThrottleMax.."\n"

		--***** CombinedThrottleAndBrake is not used in any engine files ************************************
		-- elseif Call("*:ControlExists", "CombinedThrottleAndBrake", 0) == 1 then
			-- Throttle = string.format("%d",Call( "*:GetControlValue", "CombinedThrottleAndBrake", 0 ) * 100)
			-- ThrottleType = "CombinedThrottleAndBrake"
			-- ThrottleMin = string.format("%d",Call( "*:GetControlMinimum", "CombinedThrottleAndBrake",0))
			-- ThrottleMax = string.format("%d",Call( "*:GetControlMaximum", "CombinedThrottleAndBrake", 0 ))
			-- data = data .."ThrottleType:"..ThrottleType.."\n"
			-- data = data .."ThrottleMin:"..ThrottleMin.."\n"
			-- data = data .."ThrottleMax:"..ThrottleMax.."\n"
		--****************************************************************************************************

		elseif Call("*:ControlExists", "Regulator", 0) == 1 then
			Throttle = string.format("%d",Call( "*:GetControlValue", "Regulator", 0 ) * 100)
			ThrottleType = "Regulator"
			ThrottleMin = string.format("%d",Call( "*:GetControlMinimum", "Regulator",0))
			ThrottleMax = string.format("%d",Call( "*:GetControlMaximum", "Regulator", 0 ))
		end
		data = data .."ThrottleType:"..ThrottleType.."\n"
		data = data .."ThrottleMin:"..ThrottleMin.."\n"
		data = data .."ThrottleMax:"..ThrottleMax.."\n"
		data = data .."Throttle:"..Throttle.."\n"
			
		--**********Ascertain brake type and settings **********--		
		if Call("*:ControlExists", "VirtualBrake", 0) == 1 then
			EPBrakeLever = string.format("%d",Call( "*:GetControlValue", "VirtualBrake", 0 ) * 100)
			BrakeType = "VirtualBrake"
			Min = string.format("%d",Call( "*:GetControlMinimum", "VirtualBrake",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "VirtualBrake",0))
			data = data .."BrakeType:"..BrakeType.."\n"
			data = data .."VirtualBrake:"..EPBrakeLever.."\n"
			data = data .."VirtualBrakeMin:"..Min.."\n"
			data = data .."VirtualBrakeMax:"..Max.."\n"
		end
		
		if Call("*:ControlExists", "VirtualTrainBrakeControl", 0) == 1 then
			VirtualTrainBrakeControl = string.format("%d",Call( "*:GetControlValue", "VirtualTrainBrakeControl", 0 ) * 100)
			BrakeType = "VirtualTrainBrakeControl"
			Min = string.format("%d",Call( "*:GetControlMinimum", "VirtualTrainBrakeControl",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "VirtualTrainBrakeControl",0))
			data = data .."BrakeType:"..BrakeType.."\n"
			data = data .."VirtualTrainBrakeControl:"..VirtualTrainBrakeControl.."\n"
			data = data .."VirtualTrainBrakeControlMin:"..Min.."\n"
			data = data .."VirtualTrainBrakeControlMax:"..Max.."\n"
		end
		
		if Call("*:ControlExists", "TrainBrakeControl", 0) == 1 then
			TrainBrake = string.format("%d",Call( "*:GetControlValue", "TrainBrakeControl", 0 ) * 100)
			Min = string.format("%d",Call( "*:GetControlMinimum", "TrainBrakeControl",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "TrainBrakeControl",0))
			data = data .."TrainBrakeControl:"..TrainBrake.."\n"
			data = data .."TrainBrakeControlMin:"..Min.."\n"
			data = data .."TrainBrakeControlMax:"..Max.."\n"
		end
		
		if Call("*:ControlExists", "EngineBrakeControl", 0) == 1 then
			LocoBrake = string.format("%d",Call( "*:GetControlValue", "EngineBrakeControl", 0 ) * 100)
			Min = string.format("%d",Call( "*:GetControlMinimum", "EngineBrakeControl",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "EngineBrakeControl",0))
			data = data .."EngineBrakeControl:"..LocoBrake.."\n"
			data = data .."EngineBrakeControlMin:"..Min.."\n"
			data = data .."EngineBrakeControlMax:"..Max.."\n"
		end
		
		if Call("*:ControlExists", "VirtualEngineBrakeControl", 0) == 1 then
			VirtualEngineBrakeControl = string.format("%d",Call( "*:GetControlValue", "VirtualEngineBrakeControl", 0 ) * 100)
			Min = string.format("%d",Call( "*:GetControlMinimum", "VirtualEngineBrakeControl",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "VirtualEngineBrakeControl",0))
			data = data .."VirtualEngineBrakeControl:"..VirtualEngineBrakeControl.."\n"
			data = data .."VirtualEngineBrakeControlMin:"..Min.."\n"
			data = data .."VirtualEngineBrakeControlMax:"..Max.."\n"
		end
		
		if Call("*:ControlExists", "M8Brake", 0) == 1 then
			M8Brake = string.format("%d",Call( "*:GetControlValue", "M8Brake", 0 ) * 100)
			Min = string.format("%d",Call( "*:GetControlMinimum", "M8Brake",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "M8Brake",0))
			data = data .."M8Brake:"..M8Brake.."\n"
			data = data .."M8BrakeMin:"..Min.."\n"
			data = data .."M8BrakelMax:"..Max.."\n"
		end

		if Call("*:ControlExists", "DynamicBrake", 0) == 1 then
			DynoHandle = string.format("%d",Call( "*:GetControlValue", "DynamicBrake", 0 ) * 100)
			Min = string.format("%d",Call( "*:GetControlMinimum", "DynamicBrake",0))
			Max = string.format("%d",Call( "*:GetControlMaximum", "DynamicBrake",0))
			data = data .."DynamicBrake :"..DynoHandle.."\n"
			data = data .."DynamicBrakeMin:"..Min.."\n"
			data = data .."DynamicBrakeMax:"..Max.."\n"
		end

		if Call("*:ControlExists", "EmergencyBrake", 0) == 1 then
			Emergency = string.format("%d",Call( "*:GetControlValue", "EmergencyBrake", 0 ))
			data = data .."EmergencyBrake:"..Emergency.."\n"
		end
		
		--********** Get Reverser setting **********--
		if Call("*:ControlExists", "Reverser", 0) == 1 then
			ReverseLever = string.format("%d",Call( "*:GetControlValue", "Reverser", 0 ) * 100)
			ReverserLeverMin = string.format("%d",Call("*:GetControlMinimum", "Reverser",0))
			ReverserLeverMax = string.format("%d",Call("*:GetControlMaximum", "Reverser",0))
			data = data .."Reverser:"..ReverseLever.."\n"
			data = data .."ReverserMin:"..ReverserLeverMin.."\n"
			data = data .."ReverserMax:"..ReverserLeverMax.."\n"
		end
		
		-- The virtual reverser exists on the class 360/390/90/MK3DVT and is used
		-- to control the 4 position reverser lever on their dashboards.
		if Call("*:ControlExists", "VirtualReverser", 0) == 1 then
			VirtualReverser = string.format("%d",Call( "*:GetControlValue", "VirtualReverser", 0 ) * 100)
			VirtualReverserMin = string.format("%d",Call("*:GetControlMinimum", "VirtualReverser",0))
			VirtualReverserMax = string.format("%d",Call("*:GetControlMaximum", "VirtualReverser",0))
			data = data .."VirtualReverser:"..VirtualReverser.."\n"
			data = data .."VirtualReverserMin:"..VirtualReverserMin.."\n"
			data = data .."VirtualReverserMax:"..VirtualReverserMax.."\n"
		end
		
		--********** Set cruise control **********--
		if Call("*:ControlExists", "TargetSpeed", 0) == 1 then
			CruiseControl = string.format("%d",Call( "*:GetControlValue", "TargetSpeed", 0 ))
			data = data .."TargetSpeed:"..CruiseControl.."\n"
		end
		
		--********** Gauges and warnings **********--
		if Call("*:ControlExists", "Ammeter", 0) == 1 then
			Amps = string.format("%d",Call( "*:GetControlValue", "Ammeter", 0 ))
			data = data .."Ammeter:"..Amps.."\n"
		else
			data = data .."Ammeter:0\n"
		end
		
		if Call("*:ControlExists", "RPM", 0) == 1 then
			RPM = string.format("%d",Call( "*:GetControlValue", "RPM", 0 ))
			data = data .."RPM:"..RPM.."\n"
		else
			data = data .."RPM:0\n"
		end
		
		if Call("*:ControlExists", "AWS", 0) == 1 then
			Alert = Call( "*:GetControlValue", "AWS", 0 )
			data = data .."AWS:"..Alert.."\n"
		end

		if Call("*:ControlExists", "AWSWarnCount", 0) == 1 then
			AlertCount = Call( "*:GetControlValue", "AWSWarnCount", 0 )
			data = data .."AWSWarnCount:"..AlertCount.."\n"
		end
		-------------------- Brake Gauges --------------------
		
		if Call("*:ControlExists", "TrainBrakeCylinderPressureBAR",0) == 1 then
			TrainBrakeCylinderPressureBAR = string.format("%1.1f",Call("*:GetControlValue", "TrainBrakeCylinderPressureBAR",0))
			data = data .."TrainBrakeCylinderPressureBAR:"..TrainBrakeCylinderPressureBAR.."\n"
		end
		
		if Call("*:ControlExists", "TrainBrakeCylinderPressurePSI",0) == 1 then
			TrainBrakeCylinderPressurePSI = string.format("%1.1f",Call("*:GetControlValue", "TrainBrakeCylinderPressurePSI",0))
			data = data .."TrainBrakeCylinderPressurePSI:"..TrainBrakeCylinderPressurePSI.."\n"
		end
		
		if Call("*:ControlExists", "AirBrakePipePressureBAR",0) == 1 then
			AirBrakePipePressureBAR = string.format("%1.1f",Call("*:GetControlValue", "AirBrakePipePressureBAR",0))
			data = data .."AirBrakePipePressureBAR:"..AirBrakePipePressureBAR.."\n"
		end
		
		if Call("*:ControlExists", "AirBrakePipePressurePSI",0) == 1 then
			AirBrakePipePressurePSI = string.format("%1.1f",Call("*:GetControlValue", "AirBrakePipePressurePSI",0))
			data = data .."AirBrakePipePressurePSI:"..AirBrakePipePressurePSI.."\n"
		end
		
		if Call("*:ControlExists", "BrakePipePressureBAR",0) == 1 then
			BrakePipePressureBAR = string.format("%1.1f",Call("*:GetControlValue", "BrakePipePressureBAR",0))
			data = data .."BrakePipePressureBAR:"..BrakePipePressureBAR.."\n"
		end
		
		if Call("*:ControlExists", "BrakePipePressurePSI",0) == 1 then
			BrakePipePressurePSI = string.format("%1.1f",Call("*:GetControlValue", "BrakePipePressurePSI",0))
			data = data .."BrakePipePressurePSI:"..BrakePipePressurePSI.."\n"
		end
		
		if Call("*:ControlExists", "LocoBrakeCylinderPressureBAR",0) == 1 then
			LocoBrakeCylinderPressureBAR = string.format("%1.1f",Call("*:GetControlValue", "LocoBrakeCylinderPressureBAR",0))
			data = data .."LocoBrakeCylinderPressureBAR:"..LocoBrakeCylinderPressureBAR.."\n"
		end
		
		if Call("*:ControlExists", "LocoBrakeCylinderPressurePSI",0) == 1 then
			LocoBrakeCylinderPressurePSI = string.format("%1.1f",Call("*:GetControlValue", "LocoBrakeCylinderPressurePSI",0))
			data = data .."LocoBrakeCylinderPressurePSI:"..LocoBrakeCylinderPressurePSI.."\n"
		end
		
		if Call("*:ControlExists", "MainReservoirPressureBAR",0) == 1 then
			MainReservoirPressureBAR = string.format("%1.1f",Call("*:GetControlValue", "MainReservoirPressureBAR",0))
			data = data .."MainReservoirPressureBAR:"..MainReservoirPressureBAR.."\n"
		end
		
		if Call("*:ControlExists", "MainReservoirPressurePSI",0) == 1 then
			MainReservoirPressurePSI = string.format("%1.1f",Call("*:GetControlValue", "MainReservoirPressurePSI",0))
			data = data .."MainReservoirPressurePSI:"..MainReservoirPressurePSI.."\n"
		end
		
		if Call("*:ControlExists", "VacuumBrakeChamberPressureINCHES",0) == 1 then
			VacuumBrakeChamberPressureINCHES = string.format("%1.1f",Call("*:GetControlValue", "VacuumBrakeChamberPressureINCHES",0))
			data = data .."VacuumBrakeChamberPressureINCHES:"..VacuumBrakeChamberPressureINCHES.."\n"
		end
		
		if Call("*:ControlExists", "VacuumBrakePipePressureINCHES",0) == 1 then
			VacuumBrakePipePressureINCHES = string.format("%1.1f",Call("*:GetControlValue", "VacuumBrakePipePressureINCHES",0))
			data = data .."VacuumBrakePipePressureINCHES:"..VacuumBrakePipePressureINCHES.."\n"
		end
		
		if Call("*:ControlExists", "GearLever",0) == 1 then
			GearLever = string.format("%d",Call("*:GetControlValue", "GearLever",0))
			data = data .."GearLever:"..GearLever.."\n"
		end
		
		--********** Speed limits and signal state **********--
		CurrentSpeedLimit = Call( "*:GetCurrentSpeedLimit" )
		NextSpeedLimitType, NextSpeedLimitSpeed, NextSpeedLimitDistance = Call("*:GetNextSpeedLimit", 0)
		SignalType, SignalState, SignalDistance, SignalAspect = Call("*:GetNextRestrictiveSignal", 0)
		
		if SpeedoType == "KPH" then
			CurrentSpeedLimit = string.format("%d",CurrentSpeedLimit * mpstokph)
			NextSpeedLimitSpeed = string.format("%d",NextSpeedLimitSpeed * mpstokph)
			if NextSpeedLimitSpeed == "0" then NextSpeedLimitSpeed = CurrentSpeedLimit end
			NextSpeedLimitDistance = string.format("%3.2f",(NextSpeedLimitDistance * MetresToKilometres))
			SignalDistance = string.format("%3.2f",(SignalDistance * MetresToKilometres))
		else
			CurrentSpeedLimit = string.format("%d",CurrentSpeedLimit * mpstomph)
			NextSpeedLimitSpeed = string.format("%d",NextSpeedLimitSpeed * mpstomph)
			if NextSpeedLimitSpeed == "0" then NextSpeedLimitSpeed = CurrentSpeedLimit end
			NextSpeedLimitDistance = string.format("%3.2f",(NextSpeedLimitDistance * MetresToMiles))
			SignalDistance = string.format("%3.2f",(SignalDistance * MetresToMiles))
		end

		data = data .."Current Speed Limit:"..CurrentSpeedLimit.."\n"
		data = data .."Next Speed Limit:"..NextSpeedLimitSpeed.."\n"
		data = data .."Next Speed Limit Distance:"..NextSpeedLimitDistance.."\n"
		data = data .."Signal Type:"..SignalType.."\n"
		data = data .."Signal State:"..SignalState.."\n"
		data = data .."Signal Distance:"..SignalDistance.."\n"
		data = data .."Signal Aspect:"..SignalAspect.."\n"
					
		------------------- Get doors open/closed ----------------
		if Call("*:ControlExists", "DoorsOpenCloseRight",0) == 1 then
			DoorsOpenCloseRight = string.format("%d",Call("*:GetControlValue", "DoorsOpenCloseRight",0))
			data = data .."DoorsOpenCloseRight:"..DoorsOpenCloseRight.."\n"
		end
		
		if Call("*:ControlExists", "DoorsOpenCloseLeft",0) == 1 then
			DoorsOpenCloseLeft = string.format("%d",Call("*:GetControlValue", "DoorsOpenCloseLeft",0))
			data = data .."DoorsOpenCloseLeft:"..DoorsOpenCloseLeft.."\n"
		end
		
		-------------------- Misc Gauges --------------------
		if Call("*:ControlExists", "SteamChestPressureGaugePSI",0) == 1 then
			SteamChestPressureGaugePSI = string.format("%1.1f",Call("*:GetControlValue", "SteamChestPressureGaugePSI",0))
			data = data .."SteamChestPressureGaugePSI:"..SteamChestPressureGaugePSI.."\n"
		end
		
		if Call("*:ControlExists", "SteamHeatingPressureGaugePSI",0) == 1 then
			SteamHeatingPressureGaugePSI = string.format("%1.1f",Call("*:GetControlValue", "SteamHeatingPressureGaugePSI",0))
			data = data .."SteamHeatingPressureGaugePSI:"..SteamHeatingPressureGaugePSI.."\n"
		end

		data = data .."Current Time = :"..gCurrentTime.."\n\n"
		
		-- Write data to file
		file = io.open("plugins/GetData.txt", "w")
		file:write(data)
		file:flush()
		file:close()

	end
end